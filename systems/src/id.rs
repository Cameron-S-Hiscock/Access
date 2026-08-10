use jni::{JNIEnv, objects::{JObject, JClass, JValue}};
use jni:errors:Result

pub fn gen_id<'a>(env: &mut JNIEnv<'a>, obj: &JObject<'a>) -> Result<[u8; 16]> {
    let class = env.find_class("com/cameronsh/utils/Id")?;
    let instance = env
        .get_static_field(class, "INSTANCE", "Lcom/cameronsh/utils/Id;")?
        .l()?;

    let uuid_obj = env
        .call_method(&instance, "genId", "(Ljava/lang/Object;)Ljava/util/UUID", &[(&*obj).into()])?
        .l()?;

    let msb = env.call_method(&uuid_obj, "getMostSignificantBits", "()J", &[])?.j()?;
    let lsb = env.call_method(&uuid_obj, "getLeastSignificantBits", "()J", &[])?.j()?;

    let mut bytes = [0u8; 16];
    bytes[..8].copy_from_slice(&msb.to_be_bytes());
    bytes[8..].copy_from_slice(&lsb.to_be_bytes());
    return Ok(bytes);
}
