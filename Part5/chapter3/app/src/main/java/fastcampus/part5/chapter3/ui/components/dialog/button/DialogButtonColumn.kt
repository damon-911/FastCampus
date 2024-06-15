package fastcampus.part5.chapter3.ui.components.dialog.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fastcampus.part5.chapter3.ui.components.button.PrimaryButton
import fastcampus.part5.chapter3.ui.components.button.SecondaryBorderlessButton
import fastcampus.part5.chapter3.ui.components.button.SecondaryButton
import fastcampus.part5.chapter3.ui.components.button.UnderlinedTextButton
import fastcampus.part5.chapter3.ui.model.dialog.DialogButton
import fastcampus.part5.chapter3.ui.theme.Paddings

@Composable
fun DialogButtonColumn(
    buttons: List<DialogButton>?
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons?.forEachIndexed { index, dialogButton ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(Paddings.large))
            }
            when (dialogButton) {
                is DialogButton.Primary -> {
                    PrimaryButton(
                        text = dialogButton.title,
                        leadingIconData = dialogButton.leadingIconData
                    ) { dialogButton.action?.invoke() }
                }

                is DialogButton.Secondary -> {
                    SecondaryButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }

                is DialogButton.SecondaryBorderless -> {
                    SecondaryBorderlessButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }

                is DialogButton.UnderlinedText -> {
                    UnderlinedTextButton(
                        text = dialogButton.title
                    ) { dialogButton.action?.invoke() }
                }
            }
        }
    }
}