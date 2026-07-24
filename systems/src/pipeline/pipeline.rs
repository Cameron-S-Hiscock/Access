mod linker;
mod messenger;
mod auth;

pub use crate::linker;
pub use crate::messenger;
pub use crate::auth;

pub struct pipeline{
    origin: &str,
    destination: &str,
    max_messages: i32,
}

let pipelines: Vec<pipeline> = Vec::new();

fn create_pipeline()
