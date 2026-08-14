use std::collections::VecDeque;

use crate::iostream::{
    message::Message,
};

pub struct Port {
    pub id: [u8; 16],
    pub host: [u8; 16],
    pub message_cache: VecDeque<Message>,
}

impl Port {

}
