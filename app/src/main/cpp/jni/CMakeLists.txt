project(NativeGstreamer)

add_subdirectory(gstreamer_android)

# gstreamer has this specific naming
if ("${ANDROID_ABI}" STREQUAL "arm64-v8a")
    set(GST_ABI "arm64")
elseif("${ANDROID_ABI}" STREQUAL "armeabi-v7a")
    set(GST_ABI "armv7")
else()
    set(GST_ABI ${ANDROID_ABI})
endif()

set(GSTREAMER_ROOT_ABI "${GSTREAMER_ROOT}/${GST_ABI}")

include_directories(
        ${GSTREAMER_ROOT_ABI}/include/gstreamer-1.0
        ${GSTREAMER_ROOT_ABI}/include/glib-2.0
        ${GSTREAMER_ROOT_ABI}/lib/glib-2.0/include
        ${GSTREAMER_ROOT_ABI}/lib/gstreamer-1.0/include
        ${GSTREAMER_ROOT_ABI}/include
)

# Link against zlib
find_library(
        ZLIB_LIB
        z
)

include_directories(
    ${PROJECT_SOURCE_DIR}
    ${PROJECT_SOURCE_DIR}/..
)

# Add the JNI header files to the include directories
include_directories(${JNI_INCLUDE_DIRS})

add_library(
    ${PROJECT_NAME} SHARED
    Bridge.cpp
)

target_link_directories(
        ${PROJECT_NAME} PUBLIC
        ${GSTREAMER_ROOT_ABI}/lib
        ${GSTREAMER_ROOT_ABI}/lib/gstreamer-1.0
)

find_library(loglib log PATH ${ANDROID_NDK_LIB_PATH})
find_library(androidlib android PATH ${ANDROID_NDK_LIB_PATH})

target_link_libraries(
    ${PROJECT_NAME} PUBLIC
    ${loglib}
    ${androidlib}
        gstreamer_android
)