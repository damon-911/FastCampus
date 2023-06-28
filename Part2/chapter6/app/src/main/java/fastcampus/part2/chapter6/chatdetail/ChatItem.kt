package fastcampus.part2.chapter6.chatdetail

data class ChatItem(
    var chatId: String? = null,
    val userId: String? = null,
    val message: String? = null
)