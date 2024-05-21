package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme
import kotlinx.coroutines.launch

@Composable
fun SnackbarExample() {
    var counter by remember { mutableStateOf(0) }

    // Snackbar가 suspend function이기 때문에 CoroutineScope를 사용함
    val coroutineScope = rememberCoroutineScope()

    // Snackbar는 SnackbarHost에게 SnackbarHostState를 넘겨주어 Snackbar의 state에 따라 동작됨
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Row(
            modifier = Modifier.padding(it)
        ) {
            Button(
                onClick = {
                    counter++
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "현재 counter 값은 ${counter}입니다.",
                            actionLabel = "닫기",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text("더하기")
            }
            Button(
                onClick = {
                    counter--
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "현재 counter 값은 ${counter}입니다.",
                            actionLabel = "닫기",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            ) {
                Text("빼기")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SnackbarExamplePreview() {
    Chapter4Theme {
        SnackbarExample()
    }
}