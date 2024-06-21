package fastcampus.part5.chapter4.features.detail.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import fastcampus.part5.chapter4.R
import fastcampus.part5.chapter4.features.common.entity.RestaurantDetailEntity
import fastcampus.part5.chapter4.features.detail.presentation.input.IDetailViewModelInput
import fastcampus.part5.chapter4.features.detail.presentation.output.DetailState
import fastcampus.part5.chapter4.ui_components.components.button.PrimaryButton
import fastcampus.part5.chapter4.ui_components.components.button.SecondaryButton
import fastcampus.part5.chapter4.ui_components.components.restaurant.RestaurantMeta
import fastcampus.part5.chapter4.ui_components.theme.Paddings
import fastcampus.part5.chapter4.ui_components.theme.colorSchemes
import fastcampus.part5.chapter4.ui_components.util.getAnnotatedText

@Composable
fun DetailScreen(
    restaurantDetailState: State<DetailState>,
    input: IDetailViewModelInput,
) {
    val restaurantDetail by restaurantDetailState

    if (restaurantDetail is DetailState.Main) {
        RestaurantDetail(
            restaurant = (restaurantDetail as DetailState.Main).detailEntity,
            input = input
        )
    }

    BackHandler {
        input.goBackToFeed()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantDetail(
    restaurant: RestaurantDetailEntity,
    input: IDetailViewModelInput,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier.requiredHeight(70.dp),
                navigationIcon = {
                    IconButton(onClick = { input.goBackToFeed() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(
                    horizontal = Paddings.extra,
                )
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            // Image
            BigImage(
                restaurant = restaurant
            )

            Row(
                modifier = Modifier.padding(
                    top = Paddings.xlarge
                )
            ) {
                // Meta
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    RestaurantMeta(
                        key = "Rating",
                        value = String.format("%.2f", restaurant.rating)
                    )

                    RestaurantMeta(
                        key = "Neighborhood",
                        value = restaurant.neighborhood
                    )
                }
                Spacer(Modifier.width(Paddings.large))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    RestaurantMeta(
                        key = "Cuisine Type",
                        value = restaurant.cuisineType
                    )

                    RestaurantMeta(
                        key = "Address",
                        value = restaurant.address
                    )
                }
            }

            // Title
            Text(
                text = getAnnotatedText(text = restaurant.name),
                modifier = Modifier.padding(
                    top = Paddings.extra,
                    bottom = Paddings.large
                ),
                style = MaterialTheme.typography.displaySmall
            )

            // Rating
            PrimaryButton(
                modifier = Modifier
                    .padding(top = Paddings.xlarge)
                    .fillMaxWidth(),
                text = "Add Rating Score",
                onClick = {
                    input.rateClicked()
                }
            )

            // Google Map Button
            SecondaryButton(
                modifier = Modifier
                    .padding(
                        top = Paddings.xlarge,
                        bottom = Paddings.extra
                    )
                    .fillMaxWidth(),
                text = "OPEN MAP",
                onClick = {
                    input.googleMapClicked()
                }
            )

            // Review
            Text(
                text = "Reviews",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = Paddings.xlarge)
            )

            restaurant.reviews.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = Paddings.extra
                        ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 0.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorSchemes.textFiledBackground
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                Paddings.xlarge
                            )
                    ) {
                        RestaurantMeta(
                            key = "Name",
                            value = it.name
                        )
                        RestaurantMeta(
                            key = "Date",
                            value = it.date
                        )
                        RestaurantMeta(
                            key = "Rating",
                            value = it.rating.toString()
                        )
                        RestaurantMeta(
                            key = "Comments",
                            value = it.comments
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.BigImage(
    restaurant: RestaurantDetailEntity,
) {
    Card(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(data = restaurant.photograph)
                    .apply {
                        crossfade(true)
                        scale(Scale.FILL)
                    }.build()
            ),
            modifier = Modifier
                .width(300.dp)
                .height(250.dp),
            contentDescription = "Restaurant Poster Image"
        )
    }
}