package fastcampus.part4.chapter5_plus.example

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fastcampus.part4.chapter5_plus.ui.theme.Chapter5_plusTheme
import fastcampus.part4.chapter5_plus.viewmodel.GithubViewModel

@Composable
fun DIExample(viewModel: GithubViewModel = viewModel()) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Button(onClick = {
                viewModel.getRepos()
            }) {
                Text("Repository 가져오기")
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
        items(viewModel.repos) {
            Text(it.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DIExamplePreview() {
    Chapter5_plusTheme {
        DIExample()
    }
}