#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>
#include <threads.h>
#include <time.h>

#ifdef _WIN32
    #include <windows.h>
    #define LOAD_LIB(path) LoadLibraryA(path)
    #define GET_SYM(lib, name) GetProcAddress(lib, name)
    #define LIB_HANDLE HMODULE
#else
    #include <dlfcn.h>
    #define LOAD_LIB(path) dlopen(path, RTLD_NOW)
    #define GET_SYM(lib, name) dlsym(lib, name)
    #define LIB_HANDLE void*
#endif

#ifndef ACCESS_JAR_PATH
    #define ACCESS_JAR_PATH "access.jar"
#endif

typedef jint (*CreateJavaVM_fn)(JavaVM **, void **, void *);
typedef void (*SystemsInitJvm_fn)(void *);

static JavaVM *jvm = NULL;
static JNIEnv *env = NULL;
static LIB_HANDLE jvm_lib = NULL;
static LIB_HANDLE systems_lib = NULL;
static CreateJavaVM_fn CreateJavaVM_ptr = NULL;

int jvm_warmup(void *arg) {
#ifdef _WIN32
    jvm_lib = LOAD_LIB("jvm.dll");
#else
    jvm_lib = LOAD_LIB("libjvm.so");
#endif
    if(!jvm_lib) {
        fprintf(stderr, "Failed to load JVM shared library\n");
        return 1;
    }

    CreateJavaVM_ptr = (CreateJavaVM_fn)GET_SYM(jvm_lib, "JNI_CreateJavaVM");
    if(!CreateJavaVM_ptr) {
        fprintf(stderr, "Failed to resolve JNI_CreateJavaVM\n");
        return 1;
    }

    JavaVMInitArgs vm_args;
    JavaVMOption options[2];
    options[0].optionString = "-Djava.class.path=" ACCESS_JAR_PATH;
    options[1].optionString = "-XX:+TieredCompilation";

    vm_args.version = JNI_VERSION_21;
    vm_args.nOptions = 2;
    vm_args.options = options;
    vm_args.ignoreUnrecognized = JNI_FALSE;

    jint rc = CreateJavaVM_ptr(&jvm, (void **)&env, &vm_args);
    if(rc != JNI_OK) {
        fprintf(stderr, "JNI_CreateJavaVM failed: %d\n", rc);
        return 1;
    }
    return 0;
}

int main(int argc, char *argv[]) {
    thrd_t warmup_thread;
    thrd_create(&warmup_thread, jvm_warmup, NULL);

    // TODO: Parse access.conf

    int warmup_result;
    thrd_join(warmup_thread, &warmup_result);
    if(warmup_result != 0 || !jvm || !env) {
        fprintf(stderr, "JVM warmup failed, aborting\n");
        return 1;
    }

#ifdef _WIN32
    systems_lib = LOAD_LIB("systems.dll");
#else
    systems_lib = LOAD_LIB("libsystems.so");
#endif
    if(!systems_lib) {
        fprintf(stderr, "Failed to load systems library\n");
        return 1;
    }
    SystemsInitJvm_fn SystemsInitJvm =
        (SystemsInitJvm_fn)GET_SYM(systems_lib, "SystemsInitJvm");
    if(!SystemsInitJvm) {
        fprintf(stderr, "Failed to resolve SystemsInitJvm\n");
        return 1;
    }

    SystemsInitJvm((void *)jvm);

    jclass appClass = (*env)->FindClass(env, "com/cameronsh/AppKt");
    if(!appClass) {
        fprintf(stderr, "Cound not find class com.cameronsh.AppKt\n");
        return 1;
    }

    jmethodID mainMethod = (*env)->GetStaticMethodID(env, appClass, "main", "([Ljava/lang/String;)V");
    if(!mainMethod) {
        fprintf(stderr, "Could not find AppKt.main([Ljava/lang/String;)V\n");
        return 1;
    }

    jclass stringClass = (*env)->FindClass(env, "java/lang/String");
    jobjectArray jargs = (*env)->NewObjectArray(env, argc - 1, stringClass, NULL);
    for(int i = 1; i < argc; i++) {
        jstring arg = (*env)->NewStringUTF(env, argv[i]);
        (*env)->SetObjectArrayElement(env, jargs, i - 1, arg);
        (*env)->DeleteLocalRef(env, arg);
    }

    (*env)->CallStaticVoidMethod(env, appClass, mainMethod, jargs);
    if((*env)->ExceptionCheck(env)) {
        (*env)->ExceptionDescribe(env);
    }

    (*jvm)->DestroyJavaVM(jvm);
    return 0;
}
