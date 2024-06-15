package fastcampus.part5.chapter3.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fastcampus.part5.chapter3.ui.components.dialog.button.DialogButtonColumn
import fastcampus.part5.chapter3.ui.components.dialog.content.DialogContentWrapper
import fastcampus.part5.chapter3.ui.components.dialog.title.DialogTitleWrapper
import fastcampus.part5.chapter3.ui.model.dialog.DialogButton
import fastcampus.part5.chapter3.ui.model.dialog.DialogContent
import fastcampus.part5.chapter3.ui.model.dialog.DialogTitle
import fastcampus.part5.chapter3.ui.theme.Paddings
import fastcampus.part5.chapter3.ui.theme.colorSchemes

@Composable
fun BaseDialogPopup(
    dialogTitle: DialogTitle? = null,
    dialogContent: DialogContent? = null,
    buttons: List<DialogButton>? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = Paddings.none
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorSchemes.background
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            dialogTitle?.let {
                DialogTitleWrapper(it)
            }
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .padding(
                        start = Paddings.xlarge,
                        end = Paddings.xlarge,
                        bottom = Paddings.xlarge
                    )
            ) {
                dialogContent?.let { DialogContentWrapper(it) }
                buttons?.let { DialogButtonColumn(it) }
            }
        }
    }
}