#![allow(unused)]

use std::ffi::{CStr, CString};
use std::os::raw::c_char;

mod id;
mod network;

#[unsafe(no_mangle)]
pub extern "C" fn free_str(s: *mut c_char) {
    if s.is_null() { return; }
    unsafe { drop(CString::from_raw(s)); }
}
