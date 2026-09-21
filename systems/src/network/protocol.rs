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
    error::Error, ffi::{CStr, c_char}, ptr, sync::OnceLock,
};

use crate::id;

pub const BUFFER_SIZE: usize = 1024;
pub const BUFFER_TYPE: u8 = 0u8;

pub fn request_data() -> Result<(), Box<dyn Error>> {
    return Ok(())
}

pub fn send_data() -> Result<(), Box<dyn Error>> {
    return Ok(())
}

pub fn c_addresses_to_vec(
    addresses: *const *const c_char,
    address_count: usize,
) -> Option<Vec<String>> {
    if addresses.is_null() || address_count == 0 {
        return None
    }

    let address_ptrs= unsafe {
        std::slice::from_raw_parts(addresses, address_count)
    };

    let mut result = Vec::with_capacity(address_count);

    for address_ptr in address_ptrs {
        if address_ptr.is_null() {
            return None;
        }

        let address = unsafe {
            CStr::from_ptr(*address_ptr)
        }
        .to_str()
        .ok()?
        .to_owned();

        result.push(address);
    }

    return Some(result)
}