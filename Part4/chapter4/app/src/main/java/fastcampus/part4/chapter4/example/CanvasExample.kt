package fastcampus.part4.chapter4.example

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun CanvasExample() {
    Icon(
        imageVector = Icons.Filled.Send,
        contentDescription = null,
        modifier = Modifier.size(10.dp)
    )

    Canvas(modifier = Modifier.size(50.dp)) {
        // drawLine()을 통해 선 그리기 (색상, 시작 Offset, 끝 Offset)
        drawLine(Color.Red, Offset(10f, 40f), Offset(30f, 80f))

        // drawCircle()을 통해 원 그리기 (색상, 반지름, 중앙 Offset)
        drawCircle(Color.Green, 20f, Offset(60f, 60f))

        // drawRect()을 통해 사각형 그리기 (색상, 왼쪽 상단 Offset, 오른쪽 하단 Offset)
        drawRect(Color.Blue, Offset(90f, 40f), Size(40f, 40f))

        // 아이콘(Icons.Filled.Send)을 drawLine()으로 그리기
        drawLine(Color.Magenta, Offset(32.01f, 21.0f), Offset(53.0f, 12.0f))
        drawLine(Color.Magenta, Offset(53.0f, 12f), Offset(32.01f, 3.0f))
        drawLine(Color.Magenta, Offset(32.01f, 3.0f), Offset(32.0f, 10.0f))
        drawLine(Color.Magenta, Offset(32.0f, 10.0f), Offset(47.0f, 12.0f))
        drawLine(Color.Magenta, Offset(47.0f, 12.0f), Offset(32.0f, 14.0f))
        drawLine(Color.Magenta, Offset(32.0f, 14.0f), Offset(32.01f, 21.0f))
    }
}

@Preview(showBackground = true)
@Composable
fun CanvasExamplePreview() {
    Chapter4Theme {
        CanvasExample()
    }
}