package fastcampus.part5.chapter4.ui_components.components.dialog.type

import androidx.compose.runtime.Composable
import fastcampus.part5.chapter4.ui_components.components.dialog.BaseDialogPopup
import fastcampus.part5.chapter4.ui_components.model.dialog.DialogButton
import fastcampus.part5.chapter4.ui_components.model.dialog.DialogContent
import fastcampus.part5.chapter4.ui_components.model.dialog.DialogText
import fastcampus.part5.chapter4.ui_components.model.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
    restaurantName: String,
    rating: Float,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Large("Rate your Score!"),
        dialogContent = DialogContent.Rating(
            DialogText.Rating(
                text = restaurantName,
                rating = rating
            )
        ),
        buttons = buttons
    )
}