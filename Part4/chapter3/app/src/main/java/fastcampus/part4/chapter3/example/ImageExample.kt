package fastcampus.part4.chapter3.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.R
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun ImageExample() {
    Column {
        // imageVector를 통헤 Image 생성
        Image(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Setting",
            modifier = Modifier.size(100.dp)
        )

        // painter를 통해 Image 생성
        Image(
            painter = painterResource(id = R.drawable.wall),
            contentDescription = "Antelope Canyon",
            modifier = Modifier.width(200.dp)
        )

        // bitmap을 통해 Image 생성
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.wall),
            contentDescription = "Wall",
            modifier = Modifier.width(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageExamplePreview() {
    Chapter3Theme {
        ImageExample()
    }
}