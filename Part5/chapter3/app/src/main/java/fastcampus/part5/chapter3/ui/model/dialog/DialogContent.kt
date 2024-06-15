package fastcampus.part5.chapter3.ui.model.dialog

sealed class DialogContent {
    data class Default(
        val dialogText: DialogText.Default
    ) : DialogContent()

    data class Large(
        val dialogText: DialogText.Default
    ) : DialogContent()

    data class Rating(
        val dialogText: DialogText.Rating
    ) : DialogContent()
}