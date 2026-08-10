#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include <pthread.h>

static JavaVM *jvm = NULL;
static JNIEnv *env = NULL;

void *jvm_warmup(void *arg) {
    JavaVMInitArgs vm_args;
    JavaVMOption options[2];
    options[0].optionString = "-Djava.class.path=/path/to/access.jar";
    options[1].optionString = "-XX:+TieredCompilation";

    vm_args.version = JNI_VERSION_25;
    vmargs.nOptions = 2;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_FALSE;

    JNI_CreateJavaVM(&jvm, (void**)&env, &vm_args);
    return NULL;
}

int main(int argc, char *argv[]) {
    pthread_t warmup_thread;
    pthread_create(&warmup_thread, NULL, jvm_warmup, NULL);

    // TODO: Parse access.conf

    pthread_join(warmup_thread, NULL);

    extern void systems_init_jvm(void *jvm_ptr);
    systems_init_jvm((void *)jvm);

    jclass appClass = (*env)->FindClass(env, "com/cameronsh/AppKt");

    jmethodID mainMethod = (*env)->GetStaticMethodID(env, appClass, "main", "([Ljava/lang/String;)V");

    jobjectArray jargs = (*env)->NewObjectArray(env, argc - 1, (*env)->FindClass(env, "java/lang/String"), NULL);

    (*env)->CallStaticVoidMethod(env, appClass, mainMethod, jargs);

    (*jvm)->DestroyJavaVM(jvm);
    return 0;
}
