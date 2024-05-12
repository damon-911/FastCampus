package fastcampus.part4.chapter3.example

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

// checked와 text를 인자로 받는 Composable 함수 생성
@Composable
fun CheckBoxWithText(checked: MutableState<Boolean>, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        Text(
            text = text,
            modifier = Modifier.clickable { checked.value = !checked.value }
        )
    }
}

// Composable 함수의 인자로 Composable 람다 타입의 content 추가
// content에는 Text 뿐만 아니라 다른 Composable 컴포넌트가 들어갈 수 있음
// Slot API : 서로 다른 Composable 컴포넌트를 넣을 수 있는 방식
@Composable
fun CheckBoxWithSlot(
    checked: MutableState<Boolean>,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            checked.value = !checked.value
        }
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        content()
    }
}

// Composable 함수의 인자에 상태를 바꾸는 람다 추가
// 함수 내부에 있는 상태를 바꾸는 코드 없앨 수 있음
@Composable
fun CheckBoxWithSlot2(
    checked: Boolean,
    onCheckedChanged: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChanged() }
        )
        content()
    }
}

@Composable
fun SlotExample() {
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }

    Column {
        CheckBoxWithText(checked = checked1, "Text 1")
        CheckBoxWithText(checked = checked2, "Text 2")

        Spacer(modifier = Modifier.size(16.dp))

        CheckBoxWithSlot(checked = checked1) {
            Text(text = "Text 1")
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {}) {
                Text(text = "Button1")
            }
        }
        CheckBoxWithSlot(checked = checked2) {
            Text(text = "Text 2")
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {}) {
                Text(text = "Button2")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))

        CheckBoxWithSlot2(
            checked = checked1.value,
            onCheckedChanged = { checked1.value = !checked1.value }
        ) {
            Text(text = "Text 1")
        }
        CheckBoxWithSlot2(
            checked = checked2.value,
            onCheckedChanged = { checked2.value = !checked2.value }
        ) {
            Text(text = "Text 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SlotExamplePreview() {
    Chapter3Theme {
        SlotExample()
    }
}