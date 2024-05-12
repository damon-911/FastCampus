package fastcampus.part4.chapter3.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun CoilExample() {
    Column {
        // Coil의 rememberImagePainter를 통해 이미지 생성 (Deprecated)
        val painter = rememberImagePainter(data = "https://raw.githubusercontent.com/damon-911/FastCampus/main/Part4/chapter3/app/src/main/res/drawable-hdpi/wall.jpg")
        Image(
            painter = painter,
            contentDescription = "Antelope Canyon"
        )
        
        Spacer(modifier = Modifier.size(10.dp))

        // Coil의 AsyncImage를 통해 이미지 생성
        AsyncImage(
            model = "https://raw.githubusercontent.com/damon-911/FastCampus/main/Part4/chapter3/app/src/main/res/drawable-hdpi/wall.jpg",
            contentDescription = "Wall"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoilExamplePreview() {
    Chapter3Theme {
        CoilExample()
    }
}