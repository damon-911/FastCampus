package fastcampus.part4.chapter4.example

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun EffectExample(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val snackbarHostState = remember { SnackbarHostState() }

    // LaunchedEffect : Composition이 이루어지거나 Composable 함수가 최초로 호출될 때, Composition의 CoroutineContext로 코루틴 블럭 실행
    // 키 값의 변화에 따라 중단 함수를 호출하거나 컴포저블 내부에서 외부의 값을 구독하면서 중단 함수를 호출해야 하는 경우 사용
    // LaunchedEffect 함수의 인자인 key 값이 변경되면, 현재 실행중인 코루틴은 취소되고 재시작됨 (해당 Effect가 Composition을 벗어나면 코루틴은 자동 취소됨)
    LaunchedEffect(snackbarHostState) {
        snackbarHostState.showSnackbar("Hello World!")
    }

    // DisposableEffect : 컴포저블이 Composition을 종료한 후, 컴포저블의 수명주기에 맞게 정리되어야 할 리스너가 있거나 처리해야 할 이벤트가 있는 경우 사용
    // DisposableEffect 함수의 인자인 key 값이 변경되면, 내부의 onDispose()가 실행되고 해당 Effect를 다시 호출하여 재설정함
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> {
                    Log.d("Effect", "ON_START")
                }

                Lifecycle.Event.ON_RESUME -> {
                    Log.d("Effect", "ON_RESUME")
                }

                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("Effect", "ON_PAUSE")
                }

                Lifecycle.Event.ON_STOP -> {
                    Log.d("Effect", "ON_STOP")
                }

                else -> {
                    Log.d("Effect", "ELSE")
                }
            }
        }

        // 블록 내에서 옵저버 추가
        lifecycleOwner.lifecycle.addObserver(observer)

        // onDispose에서 옵저버 제거
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = "Effect Example",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EffectExamplePreview() {
    Chapter4Theme {
        EffectExample()
    }
}