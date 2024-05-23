package fastcampus.part4.chapter3.example

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun CheckboxExample() {
    Column {
        // boolean 변수를 설정하여 checked에서 그 값을 반영 (원하는 대로 동작 X)
        Row(verticalAlignment = Alignment.CenterVertically) {
            var checked = false
            Checkbox(checked = checked, onCheckedChange = { checked = !checked })
            Text(text = "Checkbox Test")
        }

        // boolean 대신 remember { mutableStateOf(false) } 사용
        // value 프로퍼티를 통해 상태값 사용
        Row(verticalAlignment = Alignment.CenterVertically) {
            val checked = remember { mutableStateOf(false) }
            Checkbox(checked = checked.value, onCheckedChange = { checked.value = !checked.value })
            Text(text = "Checkbox Test")
        }

        // delegated 프로퍼티로 변경
        Row(verticalAlignment = Alignment.CenterVertically) {
            var checked by remember { mutableStateOf(false) }
            Checkbox(checked = checked, onCheckedChange = { checked = !checked })
            Text(text = "Checkbox Test")
        }

        // destruction으로 상태를 받아서 사용
        Row(verticalAlignment = Alignment.CenterVertically) {
            val (checked, setChecked) = remember { mutableStateOf(false) }
            Checkbox(checked = checked, onCheckedChange = setChecked)
            Text(text = "Checkbox Test")
        }

        // Text를 클릭해도 체크박스가 클릭됨
        Row(verticalAlignment = Alignment.CenterVertically) {
            val (getter, setter) = remember { mutableStateOf(false) }
            Checkbox(checked = getter, onCheckedChange = setter)
            Text(text = "Checkbox Test", modifier = Modifier.clickable { setter(!getter) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckboxExamplePreview() {
    Chapter3Theme {
        CheckboxExample()
    }
}