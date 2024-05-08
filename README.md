# gstreamer-android-with-cmake

Project to show the way how to configure Android app to work with native Gstreamer using CMake.

## How to use

In **app/src/main/cpp/cmake-external-modules/dependencies.cmake** put the path where you store gstreamer libs.

It is necessary to have **app/src/main/cpp/jni/gstreamer_android** and **app/src/main/java/org/freedesktop/gstreamer** with your other project files, since there are important auxiliary files with functions needed for proper gstreamer initialization there.

Plugins are connected in **app/src/main/cpp/jni/gstreamer_android/gstreamer_android.c** file using GST_PLUGIN_STATIC_DECLARE and GST_PLUGIN_STATIC_REGISTER.

Gstreamer logging level can be changed in **app/src/main/cpp/jni/gstreamer_android/gstreamer_android.c**:
```c
/* Set desired log-level here */
gst_debug_set_default_threshold(GST_LEVEL_ERROR);
```


Not all plugin libs are added in **app/src/main/cpp/cmake-external-modules/gstreamer-libs.cmake** file. Add there additional gstreamer libs/plugins that are demanded by your project.


## Support
Improvements and fixes are welcome.