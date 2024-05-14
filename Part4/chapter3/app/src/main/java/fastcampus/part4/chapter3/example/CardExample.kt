package fastcampus.part4.chapter3.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fastcampus.part4.chapter3.data.CardData
import fastcampus.part4.chapter3.ui.theme.Chapter3Theme

@Composable
fun CardExample() {
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
        Column {
            // 각종 컴포넌트를 이용하여 카드 레이아웃 생성
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                AsyncImage(
                    model = cardData.imageUri,
                    contentDescription = cardData.imageDescription,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        text = cardData.author
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = cardData.description
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            // placeholder : 이미지가 없을 때, 대체로 채워 넣을 항목 추가
            // ContentScale.Crop : 사이즈에 맞지 않는 부분은 잘라내어 표시
            // clip(CircleShape) : 둥근 외형으로 설정
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                AsyncImage(
                    model = cardData.imageUri,
                    placeholder = ColorPainter(placeHolderColor),
                    contentScale = ContentScale.Crop,
                    contentDescription = cardData.imageDescription,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        text = cardData.author
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = cardData.description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardExamplePreview() {
    Chapter3Theme {
        CardExample()
    }
}