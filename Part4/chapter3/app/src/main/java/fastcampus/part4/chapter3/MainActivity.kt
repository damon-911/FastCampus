package fastcampus.part4.chapter3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import fastcampus.part4.chapter3.example.ButtonExample
import fastcampus.part4.chapter3.example.ModifierExample
import fastcampus.part4.chapter3.example.SurfaceExample
import fastcampus.part4.chapter3.example.TextExample
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Text
                    TextExample("Android")

                    // Button
                    ButtonExample(onButtonClicked = {
                        Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show()
                    })

                    // Modifier
                    ModifierExample()

                    // Surface
                    SurfaceExample("Android")
                }
            }
        }
    }
}