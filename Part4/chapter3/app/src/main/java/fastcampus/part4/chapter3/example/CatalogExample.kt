package fastcampus.part4.chapter3.example

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fastcampus.part4.chapter3.R
import fastcampus.part4.chapter3.data.ItemData
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun CatalogExample(itemList: List<ItemData>) {
    // RecyclerView와 같은 기능을 하는 LazyColumn
    LazyColumn {
        items(itemList) { item ->
            Item(item)
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.title
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = itemData.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = itemData.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Chapter3Theme {
        Item(
            ItemData(
                imageId = R.drawable.a1,
                title = "해변 놀이 공원",
                description = "해변 놀이 공원입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            )
        )
    }
}