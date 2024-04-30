package fastcampus.part4.chapter3.example

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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
fun ButtonExample(onButtonClicked: () -> Unit) {
    Column {
        Button(onClick = {}) {
            Text(text = "Send")
        }

        // Icon을 Text 앞에 추가
        Button(onClick = onButtonClicked) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Text(text = "Send")
        }

        // Spacer 설정 (Icon과 Text 사이에 공간 추가)
        Button(onClick = onButtonClicked) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // enabled에 false 설정
        Button(
            onClick = onButtonClicked,
            enabled = false
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // border에 BorderStroke 설정
        Button(
            onClick = onButtonClicked,
            border = BorderStroke(5.dp, Color.Magenta)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // shape에 RoundedCornerShape 설정
        Button(
            onClick = onButtonClicked,
            border = BorderStroke(5.dp, Color.Magenta),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        // contentPadding에 PaddingValues 설정
        Button(
            onClick = onButtonClicked,
            border = BorderStroke(5.dp, Color.Magenta),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(20.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonExamplePreview() {
    Chapter3Theme {
        ButtonExample(onButtonClicked = {})
    }
}