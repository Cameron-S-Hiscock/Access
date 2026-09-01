use std::collections::VecDeque;
use std::ffi::{CStr, CString};
use std::os::raw::c_char;
use jni::{
    Env,
    objects::JObject,
};

mod id;

#[unsafe(no_mangle)]
pub extern "C" fn free_str(s: *mut c_char) {
    if s.is_null() { return; }
    unsafe { drop(CString::from_raw(s)); }
}
