use jni::JavaVM;
use once_cell::sync::OnceCell;

static JVM: OnceCell<JavaVM> = OnceCell::new();

#[unsafe(no_mangle)]
pub extern "C" fn systems_init_jvm(jvm_ptr: *mut std::ffi::c_void) {
    let vm = JavaVM::from_raw(jvm_ptr as *mut jni::sys::JavaVM).unwrap();
    JVM.set(vm).ok();
}
