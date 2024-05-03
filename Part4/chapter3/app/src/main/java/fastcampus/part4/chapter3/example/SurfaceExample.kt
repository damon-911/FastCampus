package fastcampus.part4.chapter3.example

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun SurfaceExample(name: String) {
    Column {
        Surface(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // Surface에 elevation 설정
        Surface(
            modifier = Modifier.padding(5.dp),
            tonalElevation = 10.dp
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        Surface(
            modifier = Modifier.padding(5.dp),
            shadowElevation = 10.dp
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // border 값 설정
        Surface(
            border = BorderStroke(width = 1.dp, color = Color.Magenta),
            modifier = Modifier.padding(5.dp),
            shadowElevation = 10.dp,
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // Surface의 shape 설정
        Surface(
            border = BorderStroke(width = 1.dp, color = Color.Magenta),
            modifier = Modifier.padding(5.dp),
            shadowElevation = 10.dp,
            shape = CircleShape
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }

        // Surface의 color 설정
        Surface(
            border = BorderStroke(width = 1.dp, color = Color.Magenta),
            modifier = Modifier.padding(5.dp),
            shadowElevation = 10.dp,
            shape = CircleShape,
            color = MaterialTheme.colorScheme.error
        ) {
            Text(
                text = "Hello $name!",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SurfaceExamplePreview() {
    Chapter3Theme {
        SurfaceExample("Android")
    }
}