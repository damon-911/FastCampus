package fastcampus.part4.chapter3.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun ModifierExample() {
    Column {
        Button(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // modifier에 fillMax...() 설정
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // modifier에 width과 height 설정
        Button(
            onClick = {},
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // modifier에 size를 통해 width과 height 설정
        Button(
            onClick = {},
            modifier = Modifier
                .size(200.dp, 100.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // modifier에 padding 추가
        Button(
            onClick = {},
            modifier = Modifier
                .size(200.dp, 100.dp)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // colors에 ButtonDefaults.buttonColors를 통해
        // containerColor와 contentColor 설정
        Button(
            onClick = {},
            modifier = Modifier
                .size(200.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Cyan
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search")
        }

        // Button의 enabled에 false 설정
        // Text의 modifier에 clickable 추가
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .size(200.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Cyan
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search", modifier = Modifier.clickable { })
        }

        // Text의 modifier에 offset 설정
        Button(
            onClick = {},
            modifier = Modifier
                .size(200.dp, 100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.Cyan
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.size(ButtonDefaults.IconSpacing)
            )
            Text("Search", modifier = Modifier.offset(x = 10.dp, y = 10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExamplePreview() {
    Chapter3Theme {
        ModifierExample()
    }
}