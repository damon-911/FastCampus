package fastcampus.part4.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*
                    // ConstraintLayout
                    ConstraintLayoutExample()

                    // ConstraintSet
                    ConstraintSetExample()

                    // Chain & Barrier
                    ChainAndBarrierExample()

                    // Card with ConstraintLayout
                    CardWithConstraintLayoutExample()

                    // Canvas
                    CanvasExample()

                    // Dialog
                    DialogExample()

                    // Custom Dialog
                    CustomDialogExample()

                    // Dropdown Menu
                    DropdownMenuExample()

                    // SnackBar
                    SnackbarExample()

                    // BottomAppBar
                    BottomAppBarExample()

                    // State Hoisting
                    StateHoistingExample()

                    // Animation
                    AnimationExample()

                    // Effect
                    EffectExample()
                     */
                    // ToDoApp
                    ToDoApp()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ToDoAppPreview() {
        Chapter4Theme {
            ToDoApp()
        }
    }
}