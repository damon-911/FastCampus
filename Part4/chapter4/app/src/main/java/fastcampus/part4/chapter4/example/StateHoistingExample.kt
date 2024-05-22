package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun StateHoistingExample() {
    // remember : Recomposition 될 때에는 상태를 유지할 수 있지만, 구성 전반에 변화가 생겼을 때는 상태가 유지되지 않음 (가로,세로 전환)
    // rememberSaveable : Bundle에 상태 값을 저장하기 때문에 앱을 강제로 종료하거나 구성 전반에 변화가 생기지 않은 이상 상태 값이 저장됨
    var pyeong by rememberSaveable { mutableStateOf("1") }
    var squareMeter by rememberSaveable { mutableStateOf((1 * 3.306).toString()) }

    // State Hoisting : state가 전달되는 범위를 좁히기 위해서 state를 윗단으로 끌어올리는 것
    // PyeongToSquareMeter 함수 안에서는 state를 다루지 않음 (UI만 다루고 있음)
    // 이렇게 UI와 상태를 다루는 것을 분리함으로써 그 둘의 상호 의존성을 끊을 수 있어 UI 재사용성 증가, 테스트 및 유지 관리 가능
    PyeongToSquareMeterStateless(
        pyeong,
        squareMeter
    ) {
        if (it.isBlank()) {
            pyeong = ""
            squareMeter = ""
            return@PyeongToSquareMeterStateless
        }
        val value = it.toFloatOrNull() ?: return@PyeongToSquareMeterStateless
        pyeong = it
        squareMeter = (value * 3.306).toString()
    }
}

@Composable
fun PyeongToSquareMeterStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChanged: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChanged,
            label = {
                Text("평")
            }
        )
        OutlinedTextField(
            value = squareMeter,
            onValueChange = {},
            label = {
                Text("제곱미터")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StateHoistingExamplePreview() {
    Chapter4Theme {
        StateHoistingExample()
    }
}