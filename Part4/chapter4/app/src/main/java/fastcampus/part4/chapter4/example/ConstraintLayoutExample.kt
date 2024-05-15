package fastcampus.part4.chapter4.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // createRefs()를 통해 아래 박스들의 레퍼런스를 가져옴
        // createRefs()는 여러 개의 레퍼런스를 리턴하므로 destruction으로 분해함
        val (redBox, magentaBox, greenBox, yellowBox) = createRefs()

        Box(
            // constraintsAs()를 통헤 레퍼런스 전달
            // linkTo()를 통해 anchor와 margin 설정
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Box(
            // 가운데로 위치하고 싶을 때에는 centerTo() 사용 가능
            modifier = Modifier
                .size(40.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    centerTo(parent)
                }
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    start.linkTo(magentaBox.end)
                    top.linkTo(magentaBox.bottom)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutExamplePreview() {
    Chapter4Theme {
        ConstraintLayoutExample()
    }
}