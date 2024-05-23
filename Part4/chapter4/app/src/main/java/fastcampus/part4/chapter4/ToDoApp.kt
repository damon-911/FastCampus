package fastcampus.part4.chapter4

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter4.data.ToDoData
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun ToDoApp() {
    val (text, setText) = remember { mutableStateOf("") }
    val toDoList = remember { mutableStateListOf<ToDoData>() }

    val onSubmit: (String) -> Unit = { input ->
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, input))
        setText("")
    }
    val onEdit: (Int, String) -> Unit = { key, newText ->
        val index = toDoList.indexOfFirst { it.key == key }
        toDoList[index] = toDoList[index].copy(text = newText)
    }
    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val index = toDoList.indexOfFirst { it.key == key }
        toDoList[index] = toDoList[index].copy(done = checked)
    }
    val onDelete: (Int) -> Unit = { key ->
        val index = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(index)
    }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ToDoInput(
                text = text,
                onTextChange = setText,
                onSubmit = onSubmit
            )
            // LazyColumn을 통해 toDoList 표시
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(toDoList, key = { it.key}) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = onEdit,
                        onToggle = onToggle,
                        onDelete = onDelete
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                }
            }
        }
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        // Crossfade를 통해 isEditing에 따라 다른 UI를 보여줌
        Crossfade(targetState = isEditing, label = "isEditing") {
            when (it) {
                false -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text(text = "완료")
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )
                        Button(onClick = {
                            isEditing = true
                        }) {
                            Text(text = "수정")
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            onDelete(toDoData.key)
                        }) {
                            Text(text = "삭제")
                        }
                    }
                }

                true -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (newText, setNewText) = remember { mutableStateOf(toDoData.text) }
                        OutlinedTextField(
                            value = newText,
                            onValueChange = setNewText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            onEdit(toDoData.key, newText)
                            isEditing = false
                        }) {
                            Text(text = "완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    Chapter4Theme {
        ToDo(ToDoData(1, "Test", true))
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    Chapter4Theme {
        ToDoInput("Text", {}, {})
    }
}