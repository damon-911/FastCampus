package fastcampus.part5.chapter4.ui_components.components.restaurant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fastcampus.part5.chapter4.core.entity.CategoryEntity
import fastcampus.part5.chapter4.features.feed.presentation.input.IFeedViewModelInput
import fastcampus.part5.chapter4.ui_components.theme.Paddings

@Composable
fun CategoryRow(
    categoryEntity: CategoryEntity,
    input: IFeedViewModelInput,
) {
    Column {
        CategoryTitle(categoryEntity.neighborhood)

        LazyRow(
            contentPadding = PaddingValues(
                horizontal = Paddings.large
            )
        ) {
            itemsIndexed(categoryEntity.feedEntities) { _, item ->
                RestaurantItem(
                    restaurantItem = item,
                    input = input
                )
            }
        }
    }
}


@Composable
fun CategoryTitle(genre: String) {
    Text(
        text = genre,
        modifier = Modifier.padding(
            vertical = Paddings.large,
            horizontal = Paddings.extra
        ),
        style = MaterialTheme.typography.headlineSmall
    )
}