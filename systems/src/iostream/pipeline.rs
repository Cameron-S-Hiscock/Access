use uuid::Uuid;

use crate::iostream::{
    port::Port,
    message::Message,
};

pub struct Pipeline {
    id: Uuid,
    origin: Port,
    destination: Port,
}

impl Pipeline {
    fn run(&mut self) {
        let packet: Option<Message> = self.origin.message_cache.pop_front();
        if packet != None {
            self.destination.message_cache.push_back(packet.unwrap());
        }
    }
}
