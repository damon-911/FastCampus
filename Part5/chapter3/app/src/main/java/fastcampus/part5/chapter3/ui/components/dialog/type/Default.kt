package fastcampus.part5.chapter3.ui.components.dialog.type

import androidx.compose.runtime.Composable
import fastcampus.part5.chapter3.ui.components.dialog.BaseDialogPopup
import fastcampus.part5.chapter3.ui.model.dialog.DialogButton
import fastcampus.part5.chapter3.ui.model.dialog.DialogContent
import fastcampus.part5.chapter3.ui.model.dialog.DialogText
import fastcampus.part5.chapter3.ui.model.dialog.DialogTitle

object DialogPopup

@Composable
fun DialogPopup.Default(
    title: String,
    bodyText: String,
    buttons: List<DialogButton>
) {
    BaseDialogPopup(
        dialogTitle = DialogTitle.Default(title),
        dialogContent = DialogContent.Default(
            DialogText.Default(bodyText)
        ),
        buttons = buttons
    )
}