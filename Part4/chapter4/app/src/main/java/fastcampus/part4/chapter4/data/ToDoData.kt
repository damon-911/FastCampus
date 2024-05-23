package fastcampus.part4.chapter4.data

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)