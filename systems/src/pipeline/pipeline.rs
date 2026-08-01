mod linker;
mod messenger;
mod auth;

pub use crate::linker;
pub use crate::messenger;
pub use crate::auth;

use uuid::Uuid;

pub struct Pipeline {
    origin: Uuid,
    destination: Uuid,
    max_messages: u8,
}

impl Pipeline {
    
}

let pipelines: Vec<pipeline> = Vec::new();

#[unsafe(no_mangle)]
pub extern "C" fn create_pipeline(
    origin: Uuid,
    destination: Uuid,
    max_messages: u8,
) -> Pipeline {
    let pipeline = Pipeline{
        origin: &origin,
        destination: &destination,
        max_messages: &max_messages,
    };
    pipelines.push(pipeline);
    return pipeline
}
