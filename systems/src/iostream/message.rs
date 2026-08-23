use jni::{
    objects::JObject,
};

#[derive(Copy, Clone)]
pub struct Message<'a> {
    pub id: [u8; 16],
    pub targets: [[u8; 16]; 2],
    pub task: &'a JObject<'a>,
    pub data: &'a JObject<'a>,
}

impl Message<'_> {

}
