package fastcampus.part4.chapter3.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun BoxWithConstraintsExample() {
    Outer()
}

@Composable
fun Outer() {
    // Column에 width과 height를 지정해서 제한 가능
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(240.dp)
    ) {
        // Inner의 인자로 Modifier.widthIn().heightIn() 설정
        Inner(
            modifier = Modifier
                .widthIn(min = 200.dp)
                .heightIn(min = 160.dp)
                .background(Color.Yellow)
        )
    }
}

// Inner 인자로 받은 modifier를 BoxWithConstraints에 전달
@Composable
private fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        if (minWidth >= 200.dp) {
            Text(
                text = "It's too long!",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Text("maxW:$maxWidth\nmaxH:$maxHeight\nminW:$minWidth\nminH:$minHeight\n")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxWithConstraintsExamplePreview() {
    Chapter3Theme {
        BoxWithConstraintsExample()
    }
}