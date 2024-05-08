project(gstreamer_android)

include(gstreamer-libs)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fPIC")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")

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

# Find OpenGL ES libraries
#find_library(GLESv2-lib GLESv2)
#find_library(EGL-lib EGL)
find_library(gl-lib
            GLESv3
            )

    find_library(egl-lib
            EGL
            )
find_library(OPENSL_LIBRARY OpenSLES)

include_directories(
    ${JNI_INCLUDE_DIRS}
    ${PROJECT_SOURCE_DIR}/..
    ${PROJECT_SOURCE_DIR}
    ${GST_INCLUDE_DIRS}
)

add_library(
    ${PROJECT_NAME} SHARED
    gstreamer_android.c
)

target_link_directories(
    ${PROJECT_NAME} PUBLIC
    ${GSTREAMER_ROOT_ABI}/lib
    ${GSTREAMER_ROOT_ABI}/lib/gstreamer-1.0
)

find_library(loglib log PATH ${ANDROID_NDK_LIB_PATH})

target_link_libraries(
    ${PROJECT_NAME} PUBLIC
    ${loglib}
    #${GLESv2-lib}
    #${EGL-lib}
    ${gl-lib}
    ${egl-lib}
    ${OPENSL_LIBRARY}
    ${GST_LIBS}
    ${GST_PlUGIN_LIBS}
    ${GST_PLUGINS}
)