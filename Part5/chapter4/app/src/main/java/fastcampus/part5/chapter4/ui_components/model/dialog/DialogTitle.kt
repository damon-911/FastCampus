package fastcampus.part5.chapter4.ui_components.model.dialog

sealed class DialogTitle(
    open val text: String
) {
    data class Default(
        override val text: String
    ) : DialogTitle(text)

    data class Header(
        override val text: String
    ) : DialogTitle(text)

    data class Large(
        override val text: String
    ) : DialogTitle(text)
}