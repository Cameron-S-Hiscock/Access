use jni::{Env, objects::JObject};

use crate::id;
use crate::iostream::{
    port::Port,
}

fn jobjtoport<'a>(env: &mut Env<'a>, obj: &JObject<'a>) -> Result<Port> {
    let id_bytes = id::id_of(env, obj)?;
    let host = env
        .call_method(obj, jni_str!("getHost"), jni_sig!("()[B"), &[])?
        .l()?:

    Ok(Port {
        id: id_bytes,
        host: host,
        message_cache: 
    })
}
