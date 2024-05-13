package fastcampus.part4.chapter3.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme
import fastcampus.part4.chapter3.ui.theme.Purple80

@Composable
fun CheckBoxWithContent(
    checked: Boolean,
    toggleState: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { toggleState() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { toggleState() },
        )
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var checked by remember { mutableStateOf(false) }

    // Scaffold은 TopAppBar, BottomAppBar, FloatingActionButton 및 Drawer와 같은
    // 가장 일반적인 최상위 Material Component에 대한 Slot을 제공합니다.
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Scaffold App")
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple80
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                shape = CircleShape
            ) {
                Image(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "추가"
                )
            }
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {
            CheckBoxWithContent(
                checked = checked,
                toggleState = {
                    checked = !checked
                }
            ) {
                Text(text = "Compose 공부하기")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScaffoldExamplePreview() {
    Chapter3Theme {
        ScaffoldExample()
    }
}