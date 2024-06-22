package fastcampus.part5.chapter4.features.feed.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fastcampus.part5.chapter4.ui_components.R
import fastcampus.part5.chapter4.features.feed.presentation.input.IFeedViewModelInput
import fastcampus.part5.chapter4.features.feed.presentation.output.FeedState
import fastcampus.part5.chapter4.ui_components.theme.Paddings
import timber.log.Timber

val COMMON_HORIZONTAL_PADDING = Paddings.medium
val IMAGE_SIZE = 48.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    feedStateHolder: State<FeedState>,
    input: IFeedViewModelInput,
    buttonColor: State<Color>,
    changeAppColor: () -> Unit,
) {
    val btnColor by remember { buttonColor }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.requiredHeight(70.dp),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(
                                start = COMMON_HORIZONTAL_PADDING
                            ),
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.displaySmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    AppBarMenu(
                        btnColor = btnColor,
                        changeAppColor = changeAppColor,
                        input = input
                    )
                }
            )
        }
    ) {
        BodyContent(
            feedState = feedStateHolder.value,
            input = input,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun AppBarMenu(
    btnColor: Color,
    changeAppColor: () -> Unit,
    input: IFeedViewModelInput,
) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(
                end = COMMON_HORIZONTAL_PADDING
            )
    ) {
        IconButton(
            onClick = {
                changeAppColor()
            }
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color = btnColor)
            )
        }

        IconButton(
            onClick = {
                input.openInfoDialog()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_information),
                contentDescription = "Information",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun BodyContent(
    feedState: FeedState,
    input: IFeedViewModelInput,
    modifier: Modifier,
) {
    when (feedState) {
        is FeedState.Loading -> {
            Timber.d("FeedScreen : Loading")
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is FeedState.Main -> {
            Timber.d("FeedScreen : Success")
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                itemsIndexed(feedState.categories) { _, category ->
                    CategoryRow(
                        categoryEntity = category,
                        input = input
                    )
                }
            }
        }

        is FeedState.Failed -> {
            Timber.d("FeedScreen : Error")
            RetryMessage(
                message = feedState.reason,
                input = input
            )
        }
    }
}

@Composable
fun RetryMessage(
    modifier: Modifier = Modifier,
    message: String,
    input: IFeedViewModelInput,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .requiredSize(IMAGE_SIZE),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_warning),
            contentDescription = null
        )

        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                top = Paddings.xlarge,
                bottom = Paddings.extra
            )
        )

        Button(
            onClick = { input.refreshFeed() }
        ) {
            Text("RETRY")
        }
    }
}