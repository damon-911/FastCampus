package fastcampus.part5.chapter2.ui.content

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import fastcampus.part5.chapter2.R
import fastcampus.part5.chapter2.model.memos

private val MinTitleOffset = 20.dp
private val MaxTitleOffset = 100.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun ContentScreen(memoId: Int) {
    val memo = remember(memos) { memos.single { it.id == memoId } }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Body(scroll)
        Title(memo.text) { scroll.value }
    }
}

@Composable
private fun Body(
    scroll: ScrollState
) {
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaxTitleOffset)
        )
        Column(
            modifier = Modifier
                .verticalScroll(scroll)
        ) {
            Surface(Modifier.fillMaxWidth()) {
                Column {
                    Spacer(Modifier.height(110.dp))
                    Text(
                        text = stringResource(R.string.detail_placeholder),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        modifier = HzPadding
                    )

                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun Title(
    memoText: String,
    // 상태 읽기 연기 기법
    // 값을 직접 전해주지 않고 람다를 통해 값을 전해주게 하여,
    // Composition 단계 이후에 Layout 단계에서 값을 읽게 되어 Recomposition이 일어나지 않도록 함
    scrollProvider: () -> Int
) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        modifier = Modifier
            .heightIn(min = MaxTitleOffset)
            .offset {
                val offset = (maxOffset - scrollProvider()).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = memoText,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = HzPadding
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "MEMO",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = HzPadding
        )

        Spacer(Modifier.height(8.dp))
    }
}