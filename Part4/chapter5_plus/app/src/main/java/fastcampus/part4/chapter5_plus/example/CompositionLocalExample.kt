package fastcampus.part4.chapter5_plus.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter5_plus.ui.theme.Chapter5_plusTheme

val LocalElevation = compositionLocalOf { 8.dp }

@Composable
fun CompositionLocalExample() {
    // CompositionLocalProvider을 통해 특정 블록에 암시적인 값 설정
    CompositionLocalProvider(LocalElevation provides 12.dp) {
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = LocalElevation.current
            )
        ) {
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.38f
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text("안녕하세요. 패스트캠퍼스")
                    Text("${LocalContentColor.current}")
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface) {
                        Text("스안녕하세요. 패스트캠퍼")
                        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                            Text("퍼스안녕하세요. 패스트캠")
                            Text("캠퍼스안녕하세요. 패스트")
                            Text("${LocalContentColor.current}")
                        }
                        CompositionLocalProvider(LocalContentColor provides Color.Magenta) {
                            Text("트캠퍼스안녕하세요. 패스")
                            Text("스트캠퍼스안녕하세요. 패")
                            Text("${LocalContentColor.current}")
                        }
                    }
                    Text("패스트캠퍼스안녕하세요.")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CompositionLocalExamplePreview() {
    Chapter5_plusTheme {
        CompositionLocalExample()
    }
}