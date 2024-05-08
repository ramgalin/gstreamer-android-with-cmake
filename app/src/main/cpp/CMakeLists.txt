cmake_minimum_required(VERSION 3.4.1)

project(NativeGstreamerExample)

# add path for custom auxilliary cmake scripts
list(
    APPEND CMAKE_MODULE_PATH
    "${CMAKE_CURRENT_SOURCE_DIR}/cmake-external-modules/"
)

# include cmake file with paths to root folder of dependencies
include(dependencies)

set(CMAKE_FIND_ROOT_PATH_MODE_LIBRARY BOTH)
set(CMAKE_FIND_ROOT_PATH_MODE_INCLUDE BOTH)
set(CMAKE_FIND_ROOT_PATH_MODE_PACKAGE BOTH)


if(ANDROID)
    set(
        ANDROID_NDK_LIB_PATH
              "${ANDROID_NDK}/platforms/${ANDROID_PLATFORM}/arch-${ANDROID_SYSROOT_ABI}/usr/lib/"
        CACHE
        INTERNAL "Android NDL 'lib' directory path."
    )

    add_subdirectory(jni)
endif()