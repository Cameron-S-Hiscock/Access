mod task;
mod test;

use jni::{EnvUnowned, Env};
use jni::objects::{JClass, JString};
use jni::errors::{Result as JniResult, ThrowRuntimeExAndDefault};

#[unsafe(no_mangle)]
pub extern "system" fn Java_com_cameronsh_systems_SystemsBridge_systemsTask<'local>(
    mut unowned_env: EnvUnowned<'local>,
    _class: JClass<'local>,
    input: JString<'local>,
) -> JString<'local> {
    unowned_env.with_env(|env: &mut Env<'local>| -> JniResult<JString<'local>> {
        let input: String = env.get_string(&input)?.into();
        let _result = format!("processed: {}", input);

        task::log(&input);
        test::test();

        Ok(env.new_string("done".to_string())?)
    }).resolve::<ThrowRuntimeExAndDefault>()
}
