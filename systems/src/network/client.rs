use tokio::{
    net::{
        TcpListener, TcpSocket, TcpStream, ToSocketAddrs, unix::SocketAddr
    }, runtime::Runtime,
};
use jni::{Env, objects::JObject};
use std::{
    error::Error, ffi::{CStr, c_char}, ptr, sync::OnceLock,
};

use crate::{id, network::protocol};

static RUNTIME: OnceLock<Runtime> = OnceLock::new();

#[unsafe(no_mangle)]
pub extern "C" fn client_new(
    c_addresses: *const *const c_char,
    address_count: usize,
    msb: i64,
    lsb: i64,
) -> *mut Client {
    let addresses = protocol::c_addresses_to_vec(c_addresses, address_count).unwrap();

    let mut id = [0u8; 16];
    id[..8].copy_from_slice(&msb.to_be_bytes());
    id[8..].copy_from_slice(&lsb.to_be_bytes());

    let runtime = RUNTIME.get_or_init(|| Runtime::new().expect("Tokio runtime"));

    let client = match runtime.block_on(Client::new(addresses, id)) {
        Ok(client) => client,
        Err(_) => return ptr::null_mut()
    };

    return Box::into_raw(Box::new(client))
}

#[unsafe(no_mangle)]
pub extern "C" fn client_free(client: *mut Client) {
    if !client.is_null() {
        unsafe { drop(Box::from_raw(client)); }
    }
}

pub struct Client {
    streams: Vec<TcpStream>,
    id: [u8; 16],
}

impl Client {
    pub async fn new(
        addresses: Vec<String>,
        id: [u8; 16],
    ) -> Result<Self, Box<dyn std::error::Error + Send + Sync>> {
        let mut streams = Vec::new();
        for address in addresses {
            let stream = TcpStream::connect(address).await?;
            streams.push(stream);
        }
        return Ok( Self { streams, id } )
    }

    async fn connect_to_server(address: String) -> Result<(), Box<dyn Error>> {
        return Ok(())
    }
}