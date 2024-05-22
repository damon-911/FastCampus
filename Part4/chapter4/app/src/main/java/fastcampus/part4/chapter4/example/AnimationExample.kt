package fastcampus.part4.chapter4.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun AnimationExample() {
    var helloWorldVisible by remember { mutableStateOf(true) }
    var isYellow by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(false) }

    // animateColorAsState를 통해 isYellow 상태에 따라 color 설정
    val color by animateColorAsState(
        targetValue = if (isYellow) Color.Yellow else Color.White, label = "Color"
    )
    // animateFloatAsState를 통해 isYellow 상태에 따라 alpha 설정
    val alpha by animateFloatAsState(
        targetValue = if (isYellow) 0.5f else 1.0f, label = "Alpha"
    )

    // updateTransition을 통해 isDarkMode 상태 관리
    val transition = updateTransition(
        targetState = isDarkMode,
        label = "Dark Mode Transition"
    )
    // transition에 대해 animateColor를 호출해 배경색 설정
    val backgroundColor by transition.animateColor(label = "Dark Mode Background Color") { state ->
        when (state) {
            false -> Color.White
            true -> Color.Black
        }
    }
    // transition에 대해 animateColor를 호출해 글자색 설정
    val textColor by transition.animateColor(label = "Dark Mode Text Color") { state ->
        when (state) {
            false -> Color.Black
            true -> Color.White
        }
    }
    // transition에 대해 animateFloat을 호출해 alpha 값 설정
    val alphaValue by transition.animateFloat(label = "Dark Mode Alpha Value") { state ->
        when (state) {
            false -> 0.5f
            true -> 1f
        }
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .alpha(alpha)
                .padding(16.dp)
        ) {
            // AnimatedVisibility에서 visibility에 관한 애니메이션 설정 가능
            // visible 속성을 helloWorldVisible로 지정하여 visibility 관리
            // enter 속성을 통해 우리 눈에 보일 때 어떻게 보일지 설정 (덧셈으로 결합 가능)
            // exit 속성을 통해 우리 눈에 사라질 때 어떻게 보일지 설정 (덧셈으로 결합 가능)
            AnimatedVisibility(
                visible = helloWorldVisible,
                enter = expandVertically() + slideInHorizontally(),
                exit = slideOutHorizontally()
            ) {
                Text(text = "Hello World!")
            }

            RadioButtonWithText(text = "Hello World 보이기", selected = helloWorldVisible) {
                helloWorldVisible = true
            }
            RadioButtonWithText(text = "Hello World 감추기", selected = !helloWorldVisible) {
                helloWorldVisible = false
            }

            Spacer(modifier = Modifier.size(16.dp))

            Text(text = "배경색 설정")

            RadioButtonWithText(text = "흰색", selected = !isYellow) {
                isYellow = false
            }
            RadioButtonWithText(text = "노란색", selected = isYellow) {
                isYellow = true
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .alpha(alphaValue)
                .padding(16.dp)
        ) {
            Text(text = "다크 모드 설정", color = textColor)

            RadioButtonWithText(
                text = "일반 모드",
                color = textColor,
                selected = !isDarkMode
            ) {
                isDarkMode = false
            }
            RadioButtonWithText(
                text = "다크 모드",
                color = textColor,
                selected = isDarkMode
            ) {
                isDarkMode = true
            }

            Spacer(modifier = Modifier.size(16.dp))

            // Crossfade를 이용해 isDarkMode가 참일 경우 Row로 항목을 표현하고 거짓일 경우 Column으로 표현
            Crossfade(targetState = isDarkMode, label = "Box 배치") { state ->
                when (state) {
                    false -> {
                        Row {
                            Box(
                                modifier = Modifier
                                    .background(Color.Red)
                                    .size(40.dp)
                            ) {
                                Text("1")
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Magenta)
                                    .size(40.dp)
                            ) {
                                Text("2")
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Blue)
                                    .size(40.dp)
                            ) {
                                Text("3")
                            }
                        }
                    }

                    true -> {
                        Column {
                            Box(
                                modifier = Modifier
                                    .background(Color.Red)
                                    .size(40.dp)
                            ) {
                                Text("1")
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Magenta)
                                    .size(40.dp)
                            ) {
                                Text("2")
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Blue)
                                    .size(40.dp)
                            ) {
                                Text("3")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(text = text, color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationExamplePreview() {
    Chapter4Theme {
        AnimationExample()
    }
}