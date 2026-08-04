use std::ffi::{CStr, CString};
use std::os::raw::c_char;

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
