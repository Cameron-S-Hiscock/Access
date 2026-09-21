use tokio::{
    net::{
        TcpListener,
        TcpSocket,
        TcpStream,
        ToSocketAddrs
    },
    runtime::Runtime,
};
use jni::{Env, objects::JObject};
use std::{
    ffi::{CStr, c_char},
    ptr,
    sync::OnceLock,
    error::Error,
};

use crate::id;

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
    listener: TcpListener,
    id: [u8; 16],
}

impl Server {
    pub async fn new(
        address: String,
        id: [u8; 16],
    ) -> Result<Self, Box<dyn std::error::Error + Send + Sync>> {
        let listener = TcpListener::bind(format!("{address}:0")).await?;
        return Ok( Self { listener, id } )
    }

    pub async fn run(&self) -> Result<(), Box<dyn Error>> {
        loop {
            let (stream, address) = self.listener.accept().await?;
            println!("Accepted connection from {address}");
            tokio::spawn(async move {

            });
        }

        return Ok(())
    }
}