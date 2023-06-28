package fastcampus.part2.chapter6.chatlist

data class ChatRoomItem(
    val chatRoomId: String? = null,
    val lastMessage: String? = null,
    val otherUserName: String? = null,
    val otherUserId: String? = null
)