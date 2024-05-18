package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun CustomDialogExample() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = !openDialog }) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    if (openDialog) {
        Dialog(onDismissRequest = {
            // 다이얼로그 바깥 부분을 클릭했거나 뒤로 가기 버튼을 클릭했을 경우 실행할 동작 설정
            openDialog = false
        }) {
            Surface {
                // Column, Row, Text, Button 등과 같이 여러 컴포넌트들을 사용하여 커스텀 다이얼로그 만들기
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "원하는 버튼을 클릭해주세요.\n" +
                                "+1을 누르면 값이 1 증가합니다.\n" +
                                "-1을 누르면 값이 1 감소합니다.\n"
                    )
                    Row(modifier = Modifier.align(Alignment.End)) {
                        Button(onClick = {
                            openDialog = false
                        }) {
                            Text("취소")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = {
                            counter++
                            openDialog = false
                        }) {
                            Text("+1")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(onClick = {
                            counter--
                            openDialog = false
                        }) {
                            Text("-1")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDialogExamplePreview() {
    Chapter4Theme {
        CustomDialogExample()
    }
}