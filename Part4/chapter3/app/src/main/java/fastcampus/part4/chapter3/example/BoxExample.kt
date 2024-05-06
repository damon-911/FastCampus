package fastcampus.part4.chapter3.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
fun BoxExample() {
    Column {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red)
        ) {
            Text(text = "Hello World", modifier = Modifier.align(Alignment.Center))
        }

        // Box 안에 Text 여러 개 배치
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
        ) {
            Text(text = "Jetpack", modifier = Modifier.align(Alignment.CenterEnd))
            Text(text = "Compose", modifier = Modifier.align(Alignment.BottomEnd))
        }

        // 부모 Box 안에 자식 Box 배치
        Box(
            modifier = Modifier
                .size(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Blue).align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Yellow).align(Alignment.BottomEnd)
            )
        }

        // 부모 Box의 modifier 설정을 제거하여 content 사이즈만큼 보여주기
        Box {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Magenta).align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Cyan).align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxExamplePreview() {
    Chapter3Theme {
        BoxExample()
    }
}