package com.example.colorize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.colorize.ui.theme.ColorizeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorizeTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .clickable(onClick = { changeBackgroundColor() })
                        .background(backgroundColor.value)
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val currentBackgroundColor = backgroundColor.value
    val textColor = if (currentBackgroundColor.isLight()) Color.Black else Color.White

    Text(
        text = "Tap anywhere I'm very colorful",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5.copy(color = textColor),
        modifier = Modifier.fillMaxWidth()
            .clickable { changeBackgroundColor() }
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColorizeTheme {
        Box(
            modifier = Modifier.fillMaxSize()
                .clickable(onClick = { changeBackgroundColor() })
                .background(backgroundColor.value)
        ) {
            Greeting()
        }
    }
}

private val backgroundColor = mutableStateOf(getBackgroundColor())

fun changeBackgroundColor() {
    backgroundColor.value = getBackgroundColor()
}

fun getBackgroundColor(): Color {
    return Color(
        (0..255).random(),
        (0..255).random(),
        (0..255).random(),
        255
    )
}

fun Color.isLight(): Boolean {
    val luminance = (0.299 * red + 0.587 * green + 0.114 * blue) / 255
    return luminance > 0.5
}
