package fastcampus.part4.chapter3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter3.data.ItemData
import fastcampus.part4.chapter3.example.CatalogExample
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
                    /*
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

                    // Box
                    BoxExample()

                    // BoxWithConstraints
                    BoxWithConstraintsExample()

                    // Row
                    RowExample()

                    // Column
                    ColumnExample()

                    // TextField
                    TextFieldExample()

                    // Checkbox
                    CheckboxExample()

                    // Image
                    ImageExample()

                    // Coil
                    CoilExample()

                    // Card
                    CardExample()

                    // TopBar
                    TopBarExample("Android")

                    // Slot
                    SlotExample()

                    // Scaffold
                    ScaffoldExample()
                     */
                    // Catalog
                    CatalogExample(items)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CatalogExamplePreview() {
        Chapter3Theme {
            CatalogExample(items)
        }
    }

    private val items = listOf(
        ItemData(
            imageId = R.drawable.a1,
            title = "해변 놀이 공원",
            description = "해변 놀이 공원입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a2,
            title = "캐넌",
            description = "캐넌입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a3,
            title = "워터월드",
            description = "워터월드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a4,
            title = "미국의 캐넌",
            description = "미국의 캐넌입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a5,
            title = "라스베가스",
            description = "라스베가스입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a6,
            title = "호르슈 밴드",
            description = "호르슈 밴드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a7,
            title = "미국의 길",
            description = "미국의 길입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a8,
            title = "엔텔로프 캐넌",
            description = "엔텔로프 캐넌입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
        ItemData(
            imageId = R.drawable.a9,
            title = "그랜드 캐넌",
            description = "그랜드 캐넌입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
        ),
    )
}