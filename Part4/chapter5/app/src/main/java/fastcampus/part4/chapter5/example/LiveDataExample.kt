package fastcampus.part4.chapter5.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fastcampus.part4.chapter5.ui.theme.Chapter5Theme
import fastcampus.part4.chapter5.viewmodel.ToDoViewModelWithLiveData

@Composable
fun LiveDataExample(viewModel: ToDoViewModelWithLiveData = viewModel()) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            ToDoInput(
                text = viewModel.text.observeAsState("").value,
                onTextChange = viewModel.setText,
                onSubmit = viewModel.onSubmit
            )
            val items = viewModel.toDoList.observeAsState(emptyList()).value
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(
                    items = items,
                    key = { it.key }
                ) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LiveDataExamplePreview() {
    Chapter5Theme {
        LiveDataExample()
    }
}