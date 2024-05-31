package fastcampus.part4.chapter5_plus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import fastcampus.part4.chapter5_plus.example.DIExample
import fastcampus.part4.chapter5_plus.ui.theme.Chapter5_plusTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter5_plusTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    // CompositionLocal
                    CompositionLocalExample()

                    // Navigation
                    NavigationExample()'
                     */
                    // DI
                    DIExample()
                }
            }
        }
    }
}