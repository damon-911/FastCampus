package fastcampus.part4.chapter4.example

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import fastcampus.part4.chapter4.data.CardData
import fastcampus.part4.chapter4.ui.theme.Chapter4Theme

@Composable
fun CardWithConstraintLayoutExample() {
    val cardData = CardData(
        imageUri = "https://raw.githubusercontent.com/damon-911/FastCampus/main/Part4/chapter3/app/src/main/res/drawable-hdpi/wall.jpg",
        imageDescription = "Antelope Canyon",
        author = "damon-911",
        description = "Antelope Canyon은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
    )

    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.padding(4.dp),
    ) {
        // 이전 chapter에서 만든 Card를 ConstraintLayout으로 바꾸어 다시 만들어보기
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (profileImage, author, description) = createRefs()
            AsyncImage(
                model = cardData.imageUri,
                contentDescription = cardData.imageDescription,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(profileImage) {
                        centerVerticallyTo(parent)
                        start.linkTo(parent.start, margin = 8.dp)
                    }
            )
            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author) {
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(profileImage.end, parent.end, startMargin = 8.dp, endMargin = 8.dp)
                    width = Dimension.fillToConstraints
                }
            )

            val chain = createVerticalChain(author, description, chainStyle = ChainStyle.Packed)

            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardWithConstraintLayoutExamplePreview() {
    Chapter4Theme {
        CardWithConstraintLayoutExample()
    }
}