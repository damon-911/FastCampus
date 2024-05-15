package fastcampus.part4.chapter4.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun ChainAndBarrierExample() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (redBox, yellowBox, magentaBox, text) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, margin = 20.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(parent.top, margin = 80.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(parent.top, margin = 40.dp)
                }
        )

        // createVerticalChain : 수직 방향으로 Chain 생성
        // createHorizontalChain : 수평 방향으로 Chain 생성
//        createVerticalChain(redBox, yellowBox, magentaBox)
//        createHorizontalChain(redBox, yellowBox, magentaBox)

        // 만든 Chain에 chainStyle 설정
        // ChainStyle.Packed, ChainStyle.Spread, ChainStyle.SpreadInside
        createHorizontalChain(redBox, yellowBox, magentaBox, chainStyle = ChainStyle.SpreadInside)

        // createBottomBarrier : 모든 박스들의 하단을 포함하는 Barrier 생성
        // createTopBarrier, createStartBarrier, createEndBarrier
        val barrier = createBottomBarrier(redBox, yellowBox, magentaBox)

        // 만든 Barrier에 Text의 top을 연결
        Text(
            text = "If you cannot fly then run. " +
                    "If you cannot run, then walk. " +
                    "And, if you cannot walk, then crawl, " +
                    "but whatever you do, you have to keep moving forward.",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(barrier)
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ChainAndBarrierExamplePreview() {
    Chapter4Theme {
        ChainAndBarrierExample()
    }
}