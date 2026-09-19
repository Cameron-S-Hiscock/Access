use std::net::{TcpStream, TcpListener, SocketAddr, Shutdown};
use std::io::{Read, Write, Error};
use std::result::Result;
use std::thread;
use jni::{Env, objects::{JObject}};

use crate::id;
use crate::network::{client::Client, protocal::Protocal};

pub struct Server {
    address: String,
}

impl Server {

    pub fn new(address: String) -> std::io::Result<Self> {
        return Ok(Self { address })
    }

    pub fn run(&self) -> Result<(), std::io::Error> {
        let listener = TcpListener::bind(format!("{}:{}", self.address, 0))?;
        listener.set_nonblocking(true)?;
        println!("Listening at {}", listener.local_addr()?);

        for connection in listener.incoming() {
            let stream = connection?;

            thread::spawn(move || {
                if let Err(e) = Self::handle_client(stream) {
                    eprintln!("Client error: {e}");
                }
            });
        }
        Ok(())
    }

    fn handle_client(mut stream: TcpStream) -> Result<(), std::io::Error> {
        Ok(())
    }
}