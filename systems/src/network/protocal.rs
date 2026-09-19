use std::io::{Read, Write};
use std::net::{TcpListener, TcpStream};

pub struct Protocal {
    stream: TcpStream,
}