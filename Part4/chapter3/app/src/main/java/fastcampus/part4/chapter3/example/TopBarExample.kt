package fastcampus.part4.chapter3.example

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme
import fastcampus.part4.chapter3.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarExample(name: String) {
    Column {
        // title : TopAppBar에 표시할 제목 설정
        // navigationIcon : TopAppBar의 시작 부분에 표시할 내비게이션 아이콘 설정
        // actions : TopAppBar의 끝에 표시할 액션들 설정
        // colors : TopAppBar에 사용할 색상 설정
        TopAppBar(
            title = {
                Text(text = "TopAppBar")
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "업 네비게이션"
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "검색"
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "설정"
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = "계정"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Purple80
            )
        )

        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarExamplePreview() {
    Chapter3Theme {
        TopBarExample("Android")
    }
}