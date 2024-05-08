package com.example.mytestapp

class NativeTest {
    companion object {
        init {
            System.loadLibrary("NativeGstreamer")
        }
    }

    // Create your native API

}