package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun DropdownMenuExample() {
    var expandDropdownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { expandDropdownMenu = !expandDropdownMenu }) {
            Text("드롭다운 메뉴 열기")
        }
        Text("카운터: $counter")
    }

    // 드롭다운 메뉴와 각 Item 등록
    // expanded : 메뉴 확장 여부
    // onDismissRequest : 다이얼로그 바깥 부분을 클릭했거나 뒤로 가기 버튼을 클릭했을 경우 실행할 동작
    DropdownMenu(
        expanded = expandDropdownMenu,
        onDismissRequest = {
            expandDropdownMenu = false
        }
    ) {
        DropdownMenuItem(
            onClick = {
                counter++
            },
            text = {
                Text(text = "증가")
            }
        )
        DropdownMenuItem(
            onClick = {
                counter--
            },
            text = {
                Text(text = "감소")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DropdownMenuExamplePreview() {
    Chapter4Theme {
        DropdownMenuExample()
    }
}