package fastcampus.part5.chapter3.ui.components.dialog.type

import androidx.compose.runtime.Composable
import fastcampus.part5.chapter3.ui.components.dialog.BaseDialogPopup
import fastcampus.part5.chapter3.ui.model.dialog.DialogButton
import fastcampus.part5.chapter3.ui.model.dialog.DialogContent
import fastcampus.part5.chapter3.ui.model.dialog.DialogText
import fastcampus.part5.chapter3.ui.model.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    movieName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate your Score!"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = movieName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}