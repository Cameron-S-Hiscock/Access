mod jvm;

fn main() {
    println!("Starting Initialization Process");
    jvm::init_jvm();

    // Boot into Access main function in app module
}
