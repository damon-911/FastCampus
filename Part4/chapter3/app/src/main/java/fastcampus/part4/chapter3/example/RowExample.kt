package fastcampus.part4.chapter3.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun RowExample() {
    Column {
        Row(
            modifier = Modifier
                .height(40.dp)
                .background(Color.LightGray)
        ) {
            Text(text = "첫 번째!")
            Text(text = "두 번째!")
            Text(text = "세 번째!")
        }

        // 각 Text의 modifier에 align 설정
        Row(
            modifier = Modifier
                .height(40.dp)
                .background(Color.Gray)
        ) {
            Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
            Text(text = "두 번째!", modifier = Modifier.align(Alignment.CenterVertically))
            Text(text = "세 번째!", modifier = Modifier.align(Alignment.Bottom))
        }

        // Row에 verticalAlignment 설정
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .height(40.dp)
                .background(Color.DarkGray)
        ) {
            Text(text = "첫 번째!")
            Text(text = "두 번째!", modifier = Modifier.align(Alignment.Top))
            Text(text = "세 번째!")
        }

        // Row에 horizontalArrangement 설정
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .width(200.dp)
                .height(40.dp)
                .background(Color.Yellow)
        ) {
            Text(text = "첫 번째!")
            Text(text = "두 번째!", modifier = Modifier.align(Alignment.Top))
            Text(text = "세 번째!")
        }

        // 각 Text의 modifier에 weight 설정
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(250.dp)
                .height(40.dp)
        ) {
            Text(
                text = "첫 번째!", modifier = Modifier
                    .weight(3f)
                    .background(Color.Red)
            )
            Text(
                text = "두 번째!", modifier = Modifier
                    .weight(2f)
                    .background(Color.Green)
            )
            Text(
                text = "세 번째!", modifier = Modifier
                    .weight(3f)
                    .background(Color.Blue)
            )
        }

        // Text 대신 Icon 추가 가능
        // 각 Text의 textAlign 설정
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(250.dp)
                .height(40.dp)
        ) {
            Text(
                text = "첫 번째!",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(3f)
                    .background(Color.Magenta)
            )
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "추가",
                modifier = Modifier
                    .weight(2f)
                    .background(Color.Yellow)
            )
            Text(
                text = "세 번째!",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(3f)
                    .background(Color.Cyan)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowExamplePreview() {
    Chapter3Theme {
        RowExample()
    }
}