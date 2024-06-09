package fastcampus.part5.chapter2.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import fastcampus.part5.chapter2.model.Memo
import fastcampus.part5.chapter2.model.memos

@Composable
fun HomeScreen(homeState: HomeState) {
    // 계산 최소화
    // items(memoList.sortedBy { it.id }, key = {it.id} ) { ... }
    // 위의 코드처럼 items 안에 memoList를 정렬하면서 넣으면, 매번 UI를 그릴 때마다 리스트를 정렬하게 됨
    // 이를 해결하기 위해 remember를 사용하여 정렬된 리스트를 한 번만 계산하고,
    // memoList가 변경되지 않는 한 다시 계산하지 않도록 하여 불필요한 Recomposition을 피할 수 있음
    val memoList = remember { memos.sortedBy { it.id }.toMutableStateList() }
    val onClickAction: (Int) -> Unit = {
        homeState.showContent(
            it
        )
    }

    Column {
        AddMemo(memoList)
        MemoList(onClickAction, memoList)
    }
}

@Composable
fun AddMemo(memoList: SnapshotStateList<Memo>) {
    val inputValue = remember { mutableStateOf("") }
    var count by remember { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .height(100.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            value = inputValue.value,
            onValueChange = { textFieldValue -> inputValue.value = textFieldValue }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            onClick = {
                memoList.add(
                    index = 0,
                    Memo(memoList.size, inputValue.value)
                )
                inputValue.value = ""
                count++
            },
            modifier = Modifier
                .wrapContentWidth()
                .fillMaxHeight()
        ) {
            Text("ADD\n($count)")
            // 역방향 쓰기 금지 원칙
            // UI 컴포저블 내부에서 상태를 직접 변경하지 않도록 함
            // 여기서 "count++"을 수행하면, 상태 변경이 지속적으로 일어나면서 무한 Recomposition이 일어남
            // 따라서, 이벤트 핸들러에서만 상태를 변경하도록 하여 단방향 데이터 흐름을 유지해야 함
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.MemoList(onClickAction: (Int) -> Unit, memoList: SnapshotStateList<Memo>) {
    LazyColumn(
        modifier = Modifier
            .weight(1f)
    ) {
        items(
            items = memoList,
            // key 사용
            // 반복되는 컴포저블 요소들에 고유한 키를 할당하여, Compose가 각 요소의 변경 사항을 추적할 수 있도록 함
            // 변경되지 않은 항목에 대해서 불필요한 Recomposition을 피할 수 있음
            // LazyColumn이 아닌 Column일 경우에는 -> for (memo in memoList) { key(memo.id) { ... } }
            key = { it.id }
        ) { memo ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.White)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                ),
                onClick = {
                    onClickAction(memo.id)
                }
            ) {
                Text(
                    text = memo.text,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                )
            }
        }
    }
}