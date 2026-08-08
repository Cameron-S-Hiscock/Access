mod port;
mod pipeline;
mod message;

use uuid::Uuid;
use crate::iostream::{
    port::Port,
    pipeline::Pipeline,
};

pub struct Iostream {
    id: Uuid,
    ports: [Port; 2],
    pipelines: [Pipeline; 2],
}

impl Iostream {

}
