package fastcampus.part4.chapter5.data

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)