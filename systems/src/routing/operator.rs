use uuid::Uuid;
use crate::routing::{
    linker::Linker,
};

pub struct Operator<'a> {
    pub linker: &'a Linker,
}

impl Operator<'_> {
    fn path(ids: [Uuid; 2]) {

    }
}
