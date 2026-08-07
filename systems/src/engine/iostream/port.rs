use std::collections::VecDeque;
use uuid::Uuid;
use crate::engine::iostream;
use super::message::Message;

pub struct Port {
    host: Uuid,
    message_cache: VecDeque<Message>,
}

impl Port {
    fn send(&mut self, message: Message) {
        self.message_cache.push_back(message);
    }

    fn receive(&mut self) -> Option<Message> {
        return self.message_cache.pop_front()
    }
}
