package fastcampus.part5.chapter3.ui.components.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fastcampus.part5.chapter3.ui.theme.MovieAppTheme
import fastcampus.part5.chapter3.ui.theme.Paddings
import fastcampus.part5.chapter3.ui.theme.colorSchemes

@Composable
fun SecondaryBorderlessButton(
    modifier: Modifier = Modifier,
    @StringRes id: Int? = null,
    text: String = "",
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorSchemes.background,
            contentColor = MaterialTheme.colorSchemes.secondary,
            disabledContentColor = MaterialTheme.colorSchemes.background,
            disabledContainerColor = MaterialTheme.colorSchemes.disabledSecondary
        ),
        elevation = null
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = id?.let { stringResource(id = id) } ?: text,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(Paddings.small)
            )
        }
    }
}

@Preview
@Composable
fun SecondaryBorderlessButtonPreview() {
    MovieAppTheme {
        SecondaryBorderlessButton(
            text = "CANCEL"
        ) {}
    }
}