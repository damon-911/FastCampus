package fastcampus.part3.chapter5.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import fastcampus.part3.chapter5.databinding.ItemImageBinding
import fastcampus.part3.chapter5.list.ItemHandler
import fastcampus.part3.chapter5.model.ImageItem
import fastcampus.part3.chapter5.model.ListItem

class ImageItemViewHolder(
    private val binding: ItemImageBinding,
    private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as ImageItem
        binding.item = item
        binding.handler = itemHandler
    }
}