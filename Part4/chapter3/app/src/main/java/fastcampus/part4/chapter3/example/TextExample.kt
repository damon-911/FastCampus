package fastcampus.part4.chapter3.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextExample(name: String) {
    Column {
        // 기본
        Text(text = "Hello $name!")

        // color 설정
        Text(color = Color.Red, text = "Hello $name!")

        // 해쉬값으로 color 설정 (ARGB 순)
        Text(color = Color(0xFFFF9944), text = "Hello $name!")

        // fontSize 설정
        Text(color = Color(0xFFFF9944), text = "Hello $name!", fontSize = 30.sp)

        // fontWeight 설정
        Text(
            color = Color(0xFFFF9944),
            text = "Hello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        // fontFamily 설정
        Text(
            color = Color(0xFFFF9944),
            text = "Hello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        // letterSpacing 설정
        Text(
            color = Color(0xFFFF9944),
            text = "Hello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            letterSpacing = 2.sp
        )

        // maxLines 설정
        Text(
            color = Color(0xFFFFABCD),
            text = "Hello $name!\nHello $name!\nHello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            letterSpacing = 2.sp,
            maxLines = 2
        )

        // textDecoration 설정
        Text(
            color = Color(0xFFFF7696),
            text = "Hello $name!\nHello $name!\nHello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            letterSpacing = 2.sp,
            maxLines = 2,
            textDecoration = TextDecoration.Underline
        )

        // textAlign 설정
        Text(
            modifier = Modifier.width(300.dp),
            color = Color(0xFFFF2389),
            text = "Hello $name!\nHello $name!\nHello $name!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            letterSpacing = 2.sp,
            maxLines = 2,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center
        )
    }
}