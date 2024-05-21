package fastcampus.part4.chapter4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import fastcampus.part4.chapter4.example.CanvasExample
import fastcampus.part4.chapter4.example.CardWithConstraintLayoutExample
import fastcampus.part4.chapter4.example.ChainAndBarrierExample
import fastcampus.part4.chapter4.example.ConstraintLayoutExample
import fastcampus.part4.chapter4.example.ConstraintSetExample
import fastcampus.part4.chapter4.example.CustomDialogExample
import fastcampus.part4.chapter4.example.DialogExample
import fastcampus.part4.chapter4.example.DropdownMenuExample
import fastcampus.part4.chapter4.example.SnackbarExample
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
                     */
                    // SnackBar
                    SnackbarExample()
                }
            }
        }
    }
}