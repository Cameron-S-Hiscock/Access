pub mod port;
pub mod pipeline;
pub mod message;

use crate::iostream::{
    port::Port,
    pipeline::Pipeline,
    message::Message,
};

pub struct Iostream {
    pub id: [u8; 16],
    pub targets: [[u8; 16]; 2],
    pub ports: [Port; 2],
    pub pipelines: [Pipeline; 2],
}

impl Iostream {
    fn send(target: Port, message: Message) {
        
    }

    fn receive(target: Port) -> Message {
        return *target.message_cache.front().unwrap()
    }
}
