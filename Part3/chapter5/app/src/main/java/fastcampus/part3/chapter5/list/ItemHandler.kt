package fastcampus.part3.chapter5.list

import fastcampus.part3.chapter5.model.ListItem

interface ItemHandler {
    fun onClickFavorite(item: ListItem)
}