pub mod port;
pub mod pipeline;
pub mod message;

use crate::iostream::{
    port::Port,
    pipeline::Pipeline,
    message::Message,
};

#[repr(C)]
pub struct Iostream<'a> {
    pub id: [u8; 16],
    pub targets: [[u8; 16]; 2],
    pub ports: [Port<'a>; 2],
    pub pipelines: [Pipeline; 2],
}

impl Iostream<'_> {
    fn send<'a>(target: &mut Port<'a>, message: Message<'a>) {
        target.message_cache.push_back(message);
    }

    fn receive<'a>(target: &mut Port<'a>) -> Message<'a> {
        return *target.message_cache.front().unwrap()
    }
}
