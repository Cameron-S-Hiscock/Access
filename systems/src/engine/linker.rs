use uuid::Uuid;
use crate::engine::iostream;

fn link(ids: [Uuid; 2]) {
    for id in ids {
        println!("{}", id);
    }
}
