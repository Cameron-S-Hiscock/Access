pub mod pipeline;
pub mod port;
pub mod message;

use uuid::Uuid;
use port::Port;
use pipeline::Pipeline;

pub struct Iostream {
    id: Uuid,
    ports: [Port; 2],
    pipelines: [Pipeline; 2],
}

impl Iostream {

}
