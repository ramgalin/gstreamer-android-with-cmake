package com.example.mytestapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mytestapp.ui.theme.MytestappTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import org.freedesktop.gstreamer.GStreamer


class MainActivity : ComponentActivity() {
    init {
        System.loadLibrary("gstreamer_android")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MytestappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppContent()
                }
            }
        }

        // init gstreamer
        try {
            GStreamer.init(this)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Gstreamer","Couldn't init gstreamer")
            finish()
            return
        }
    }
}

@Composable
fun MyAppContent() {
    // You can use state to track whether the button is clicked
    var buttonClicked by remember { mutableStateOf(false) }
    var nativeTest = NativeTest()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (buttonClicked) {
            // If button is clicked, show a message
            Text("Started. Please see the logs")
        } else {
            // If button is not clicked, show the button
            StartButton(onClick = { buttonClicked = true })
        }
    }
}

@Composable
fun StartButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Start")
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppContentPreview() {
    MytestappTheme {
        MyAppContent()
    }
}