use std::collections::VecDeque;
use std::ffi::{CStr, CString};
use std::os::raw::c_char;
use jni::{
    Env,
    objects::JObject,
};

use crate::iostream::{
    Iostream,
    port::Port,
    pipeline::Pipeline,
    message::Message,
};

mod iostream;
mod id;

#[unsafe(no_mangle)]
pub extern "C" fn systems_log(message: *const c_char) -> *mut c_char {
    let message = unsafe { CStr::from_ptr(message) }.to_str().unwrap_or("systems_log_default");
    CString::new(format!("{}", message)).unwrap().into_raw()
}

#[unsafe(no_mangle)]
pub extern "C" fn free_str(s: *mut c_char) {
    if s.is_null() { return; }
    unsafe { drop(CString::from_raw(s)); }
}

#[unsafe(no_mangle)]
pub extern "C" fn systems_add(a: i32, b: i32) -> i32 {
    a + b
}

#[repr(C)]
pub struct Targets {
    pub values: [[u8; 16]; 2],
}

#[unsafe(no_mangle)]
pub extern "C" fn create_iostream<'a>(env: &'a mut Env<'a>, obj: &JObject<'a>, id_targets: Targets) -> *mut Iostream<'a> {
    let iostream_obj = Iostream {
        id: id::gen_id(env, obj).unwrap(),
        targets: id_targets.values,
        ports: [
            Port {
                id: id::gen_id(env, obj).unwrap(),
                host: id_targets.values[0],
                message_cache: VecDeque::new(),
            },
            Port {
                id: id::gen_id(env, obj).unwrap(),
                host: id_targets.values[1],
                message_cache: VecDeque::new(),
            },
        ],
        pipelines: [
            Pipeline {
                id: id::gen_id(env, obj).unwrap(),
                origin: 0,
                destination: 1,
            },
            Pipeline {
                id: id::gen_id(env, obj).unwrap(),
                origin: 1,
                destination: 0,
            },
        ],
    };
    return Box::into_raw(Box::new(iostream_obj))
}

#[unsafe(no_mangle)]
pub extern "C" fn create_message<'a>(env: &'a mut Env<'a>, obj: &JObject<'a>, id_targets: Targets, task: &'a JObject<'a>, data: &'a JObject<'a>) -> *mut Message<'a> {
    let message_obj = Message {
        id: id::gen_id(env, obj).unwrap(),
        targets: id_targets.values,
        task: task,
        data: data,
    };
    return Box::into_raw(Box::new(message_obj))
}
