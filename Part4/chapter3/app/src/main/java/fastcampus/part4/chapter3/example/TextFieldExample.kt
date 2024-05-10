package fastcampus.part4.chapter3.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun TextFieldExample() {
    var name by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "입력값 : $name")

        Spacer(modifier = Modifier.size(16.dp))

        // Text에 TextField 입력값 출력
        TextField(value = name, onValueChange = { name = it })

        Spacer(modifier = Modifier.size(16.dp))

        // TextField에 label 추가
        TextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text("이름")
            }
        )

        Spacer(modifier = Modifier.size(16.dp))

        // TextField 대신 OutlinedTextField로 설정
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text("이름")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldExamplePreview() {
    Chapter3Theme {
        TextFieldExample()
    }
}