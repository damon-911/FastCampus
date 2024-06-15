package fastcampus.part5.chapter3.ui.components.dialog.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part5.chapter3.R
import fastcampus.part5.chapter3.ui.model.button.LeadingIconData
import fastcampus.part5.chapter3.ui.model.dialog.DialogButton
import fastcampus.part5.chapter3.ui.theme.MovieAppTheme

@Preview
@Composable
fun AlertPreview() {
    MovieAppTheme {
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
    MovieAppTheme {
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
    MovieAppTheme {
        DialogPopup.Rating(
            movieName = "The Lord of the Rings: The Two Towers",
            rating = 7.5f,
            buttons = listOf(
                DialogButton.Primary(
                    title = "SUBMIT",
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