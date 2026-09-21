use tokio::{
    io::{
        AsyncWriteExt,
        AsyncReadExt,
    }, net::{
        TcpListener,
        TcpSocket,
        TcpStream,
        ToSocketAddrs
    }, runtime::Runtime,
};
use jni::{Env, objects::JObject};
use std::{
    error::Error, ffi::{CStr, c_char}, os::unix::net::SocketAddr, ptr, sync::OnceLock,
};

use crate::id;
use crate::network::protocol::{
    BUFFER_SIZE,
    BUFFER_TYPE,
};

static RUNTIME: OnceLock<Runtime> = OnceLock::new();

#[unsafe(no_mangle)]
pub extern "C" fn server_new(
    address: *const c_char,
    msb: i64,
    lsb: i64,
) -> *mut Server {
    if address.is_null() {
        return ptr::null_mut()
    }

    let address = match unsafe { CStr::from_ptr(address) }.to_str() {
        Ok(address) => address.to_owned(),
        Err(_) => return ptr::null_mut(),
    };

    let mut id = [0u8; 16];
    id[..8].copy_from_slice(&msb.to_be_bytes());
    id[8..].copy_from_slice(&lsb.to_be_bytes());

    let runtime = RUNTIME.get_or_init(|| Runtime::new().expect("Tokio runtime"));

    let server = match runtime.block_on(Server::new(address, id)) {
        Ok(server) => server,
        Err(_) => return ptr::null_mut()
    };

    return Box::into_raw(Box::new(server))
}

#[unsafe(no_mangle)]
pub extern "C" fn server_free(server: *mut Server) {
    if !server.is_null() {
        unsafe { drop(Box::from_raw(server)); }
    }
}

pub struct Server {
    listeners: Vec<TcpListener>,
    id: [u8; 16],
}

impl Server {
    pub async fn new(
        address: String,
        id: [u8; 16],
    ) -> Result<Self, Box<dyn std::error::Error + Send + Sync>> {
        let mut listeners = Vec::new();
        let listener = TcpListener::bind(format!("{address}:0")).await?;
        listeners.push(listener);
        return Ok( Self { listeners, id } )
    }

    pub async fn run(&self) -> Result<(), Box<dyn Error>> {
        loop {
            for listener in &self.listeners {
                let (stream, address) = listener.accept().await?;
                println!("Accepted connection from {address}");
                tokio::spawn(async move {
                    Self::handle_client(stream, address);
                });
            }
        }

        return Ok(())
    }

    async fn handle_client(mut stream: TcpStream, address: std::net::SocketAddr) -> Result<(), Box<dyn Error>> {
        let mut buffer = [0u8; BUFFER_SIZE];

        match async {
            loop {
                let n = stream.read(&mut buffer).await?;
                if n == 0 {
                    return Ok::<(), std::io::Error>(());
                }

                stream.write_all(&buffer[..n]).await?;
            }
        }.await {
            Ok(_) => println!("Client {address} disconnected"),
            Err(e) => eprintln!("Client {address} error: {e}"),
        }

        return Ok(())
    }
}