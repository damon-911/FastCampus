package fastcampus.part3.chapter5.model

import java.util.Date

interface ListItem {
    val thumbnailUrl: String
    val dateTime: Date
    var isFavorite: Boolean
}