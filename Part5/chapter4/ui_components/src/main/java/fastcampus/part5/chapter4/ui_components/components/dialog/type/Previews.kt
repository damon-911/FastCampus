package fastcampus.part5.chapter4.ui_components.components.dialog.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part5.chapter4.ui_components.R
import fastcampus.part5.chapter4.ui_components.model.button.LeadingIconData
import fastcampus.part5.chapter4.ui_components.model.dialog.DialogButton
import fastcampus.part5.chapter4.ui_components.theme.RestaurantAppTheme

@Preview
@Composable
fun AlertPreview() {
    RestaurantAppTheme {
        DialogPopup.Alert(
            title = "Title",
            bodyText = "Content",
            buttons = listOf(
                DialogButton.UnderlinedText("Okay") {}
            )
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    RestaurantAppTheme {
        DialogPopup.Default(
            title = "Title",
            bodyText = "Content",
            buttons = listOf(
                DialogButton.Primary("OPEN") {},
                DialogButton.SecondaryBorderless("CANCEL") {}
            )
        )
    }
}

@Preview
@Composable
fun RatingPreview() {
    RestaurantAppTheme {
        DialogPopup.Rating(
            restaurantName  = "Emily",
            rating = 7.5f,
            buttons = listOf(
                DialogButton.Primary(
                    title = "OPEN",
                    leadingIconData = LeadingIconData(
                        iconDrawable = R.drawable.ic_send,
                        iconContentDescription = null
                    )
                ) {},
                DialogButton.Secondary("CANCEL") {}
            )
        )
    }
}