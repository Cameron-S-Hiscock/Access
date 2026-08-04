mod engine;

use uuid::Uuid;

pub struct Pipeline {
    origin: Uuid,
    destination: Uuid,
}

impl Pipeline {
    #[unsafe(no_mangle)]
    pub extern "C" fn transmit(&messageId: Uuid) {

    }   
}

#[unsafe(no_mangle)]
pub extern "C" fn create_pipeline(
    origin: Uuid,
    destination: Uuid,
) -> Pipeline {
    let pipeline = Pipeline {
        origin: &origin,
        destination: &destination,
    };
    return pipeline
}
