use std::collections::VecDeque;
use uuid::Uuid;

use crate::iostream::{
    message::Message,
};

pub struct Port {
    pub id: Uuid,
    pub host: Uuid,
    pub message_cache: VecDeque<Message>,
}

impl Port {
    fn send(&mut self, message: Message) {
        self.message_cache.push_back(message);
    }

    fn receive(&mut self) -> Option<Message> {
        return self.message_cache.pop_front()
    }
}
