#include <thread>

#include <jni.h>
#include <android/log.h>

#include <string.h>
#include <stdint.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <gst/gst.h>
#include <gst/video/video.h>
#include <gst/video/videooverlay.h>
#include <pthread.h>
#include <gst/gl/gstgl_fwd.h>

static JavaVM *java_vm;

#ifdef __cplusplus
extern "C" {
#endif

/* Library initializer */
JNIEXPORT jint JNICALL
JNI_OnLoad (JavaVM* vm, void* reserved)
{
    JNIEnv* env = NULL;

    java_vm = vm;

    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_4) != JNI_OK) {
        __android_log_print (ANDROID_LOG_ERROR, "mytestapp",
                             "Could not retrieve JNIEnv");
        return JNI_ERR;
    }

    __android_log_print (ANDROID_LOG_DEBUG, "mytestapp",
                         "In OnLoad");

    return JNI_VERSION_1_4;
}


JNIEXPORT void JNICALL JNI_OnUnload(JavaVM* vm, void* reserved) {
    __android_log_print (ANDROID_LOG_DEBUG, "mytestapp",
                         "In OnUnload");
}

#ifdef __cplusplus
}
#endif