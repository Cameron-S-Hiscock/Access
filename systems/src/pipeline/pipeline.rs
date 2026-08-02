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
}

impl Pipeline {
    
}

let pipelines: Vec<pipeline> = Vec::new();

#[unsafe(no_mangle)]
pub extern "C" fn create_pipeline(
    origin: Uuid,
    destination: Uuid,
) -> Pipeline {
    let pipeline = Pipeline{
        origin: &origin,
        destination: &destination,
    };
    pipelines.push(pipeline);
    return pipeline
}
