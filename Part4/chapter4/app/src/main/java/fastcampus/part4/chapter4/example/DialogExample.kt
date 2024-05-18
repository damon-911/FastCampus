package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun DialogExample() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    if (openDialog) {
        AlertDialog(onDismissRequest = {
            // 다이얼로그 바깥 부분을 클릭했거나 뒤로 가기 버튼을 클릭했을 경우 실행할 동작 설정
            openDialog = false
        }, confirmButton = {
            // 확인 버튼을 클릭했을 경우 실행할 동작 설정
            Button(onClick = {
                counter++
                openDialog = false
            }) {
                Text("더하기")
            }
        }, dismissButton = {
            // 취소 버튼을 클릭했을 경우 실행할 동작 설정
            Button(onClick = {
                openDialog = false
            }) {
                Text("취소")
            }
        }, title = {
            // 다이얼로그 타이틀 설정
                   Text(text = "더하기")
        }, text = {
            // 다이얼로그 본문 설정
            Text(text = "더하기 버튼을 누르면 카운터가 증가합니다.")
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DialogExamplePreview() {
    Chapter4Theme {
        DialogExample()
    }
}