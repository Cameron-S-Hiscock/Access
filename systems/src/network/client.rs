use std::net::{TcpStream, TcpListener};
use std::io::{Read, Write, Error};
use std::result::Result;

use crate::id;
use crate::network::{server::Server, protocal::Protocal};

pub struct Client {
    address: String,
}

impl Client {
    pub fn new(address: String) -> Self {
        return Self { address }
    }

    pub fn connect(&self) -> Result<(), Error> {
        let stream = TcpStream::connect(format!("{}:{}", self.address, 0))?;

        Ok(())
    }
}