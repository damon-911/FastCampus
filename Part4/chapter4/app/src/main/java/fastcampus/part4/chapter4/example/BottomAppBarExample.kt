package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme
import kotlinx.coroutines.launch

@Composable
fun BottomAppBarExample() {
    var counter by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Snackbar를 만든 것과 비슷하게 BottomAppBar 생성
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomAppBar {
                Button(onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "안녕하세요!"
                        )
                    }
                }) {
                    Text("인사하기")
                }
                Button(onClick = {
                    counter++
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "counter 값 증가!"
                        )
                    }
                }) {
                    Text("더하기")
                }
                Button(onClick = {
                    counter--
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "counter 값 감소!"
                        )
                    }
                }) {
                    Text("빼기")
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = "현재 counter 값은 ${counter}입니다.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    // 단계 2: `bottomBar` 파라미터에 `BottomAppBar`를 넣읍시다.
    // 내용은 텍스트와 버튼을 넣어 봅시다. 버튼에는 `snackBar`를
    // 연동해 메시지를 출력합니다.

    // 단계 3: 더하기와 빼기 버튼을 추가로 만들고 `MutableState`
    // 만듭시다. `Scaffold`의 `content`에 `Text`를 넣어 카운터를 출력하게
    // 합시다.
}

@Preview(showBackground = true)
@Composable
fun BottomAppBarExamplePreview() {
    Chapter4Theme {
        BottomAppBarExample()
    }
}