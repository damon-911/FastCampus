package fastcampus.part4.chapter3.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun ColumnExample() {
    Column {
        Column(
            modifier = Modifier
                .size(100.dp)
        ) {
            Text(text = "첫 번째")
            Text(text = "두 번째")
            Text(text = "세 번째")
        }

        // Column에 horizontalAlignment 설정
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray)
        ) {
            Text(text = "첫 번째")
            Text(text = "두 번째")
            Text(text = "세 번째")
        }

        // Column에 verticalArrangement 설정
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray)
        ) {
            Text(text = "첫 번째")
            Text(text = "두 번째")
            Text(text = "세 번째")
        }

        // 각 Text의 modifier에 align 설정
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(100.dp)
                .background(Color.DarkGray)
        ) {
            Text(
                text = "첫 번째",
                modifier = Modifier
                    .align(Alignment.End)
            )
            Text(text = "두 번째")
            Text(
                text = "세 번째",
                modifier = Modifier
                    .align(Alignment.Start)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnExamplePreview() {
    Chapter3Theme {
        ColumnExample()
    }
}