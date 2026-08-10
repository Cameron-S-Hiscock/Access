use std::collections::VecDeque;
use uuid::Uuid;

mod port;
mod pipeline;
mod message;

use crate::iostream::{
    port::Port,
    pipeline::Pipeline,
};

pub struct Iostream {
    pub id: Uuid,
    pub targets: [Uuid; 2],
}

impl Iostream {
    fn create(targets: [Uuid; 2]) {
        for target in targets {
            let port = Port {
                id: Uuid::new_v4(),
                host: target,
                message_cache: VecDeque::new(),
            };
        }
    }
}
