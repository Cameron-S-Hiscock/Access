use jni::{
    Env,
    objects::{JObject},
    jni_str,
    jni_sig,
    errors::{Result},
    JValue,
};

pub fn gen_id<'a>(env: &mut Env<'a>, obj: &JObject<'a>) -> Result<[u8; 16]> {
    let class = env.find_class(jni_str!("com/cameronsh/utils/Id"))?;
    let instance = env
        .get_static_field(class, jni_str!("INSTANCE"), jni_sig!("Lcom/cameronsh/utils/Id;"))?
        .l()?;

    let uuid_obj = env
        .call_method(&instance, jni_str!("genId"), jni_sig!("(Ljava/lang/Object;)Ljava/util/UUID;"), &[(&*obj).into()])?
        .l()?;

    let msb = env.call_method(&uuid_obj, jni_str!("getMostSignificantBits"), jni_sig!("()J"), &[])?.j()?;
    let lsb = env.call_method(&uuid_obj, jni_str!("getLeastSignificantBits"), jni_sig!("()J"), &[])?.j()?;

    let mut bytes = [0u8; 16];
    bytes[..8].copy_from_slice(&msb.to_be_bytes());
    bytes[8..].copy_from_slice(&lsb.to_be_bytes());

    return Ok(bytes)
}

pub fn obj_of<'a>(env: &mut Env<'a>, id: &[u8; 16]) -> Result<JObject<'a>> {
    let mut msb_bytes = [0u8; 8];
    let mut lsb_bytes = [0u8; 8];
    msb_bytes.copy_from_slice(&id[..8]);
    lsb_bytes.copy_from_slice(&id[8..]);
    let msb = i64::from_be_bytes(msb_bytes);
    let lsb = i64::from_be_bytes(lsb_bytes);

    let uuid_class = env.find_class(jni_str!("java/util/UUID"))?;
    let uuid_obj = env.new_object(
        uuid_class,
        jni_sig!("(JJ)V"),
        &[JValue::from(msb), JValue::from(lsb)],
    )?;

    let class = env.find_class(jni_str!("com/cameronsh/utils/Id"))?;
    let instance = env
        .get_static_field(class, jni_str!("INSTANCE"), jni_sig!("Lcom/cameronsh/utils/Id;"))?
        .l()?;

    let obj = env
        .call_method(&instance, jni_str!("objOf"), jni_sig!("(Ljava/lang/UUID;)Ljava/util/Object;"), &[JValue::from(&uuid_obj)])?
        .l()?;
    
    return Ok(obj)
}

pub fn id_of<'a>(env: &mut Env<'a>, obj: &JObject<'a>) -> Result<[u8; 16]> {
    let class = env.find_class(jni_str!("com/cameronsh/utils/Id"))?;
    let instance = env
        .get_static_field(class, jni_str!("INSTANCE"), jni_sig!("Lcom/cameronsh/utils/Id;"))?
        .l()?;

    let idtoobj_obj = env
        .call_method(&instance, jni_str!("idOf"), jni_sig!("(Ljava/lang/Object;)Ljava/util/UUID;"), &[(&*obj).into()])?
        .l()?;

    let msb = env.call_method(&idtoobj_obj, jni_str!("getMostSignificantBits"), jni_sig!("()J"), &[])?.j()?;
    let lsb = env.call_method(&idtoobj_obj, jni_str!("getLeastSignificantBits"), jni_sig!("()J"), &[])?.j()?;

    let mut bytes = [0u8; 16];
    bytes[..8].copy_from_slice(&msb.to_be_bytes());
    bytes[8..].copy_from_slice(&lsb.to_be_bytes());

    return Ok(bytes)
}
