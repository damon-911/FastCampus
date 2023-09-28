package fastcampus.part3.chapter3.detail

import androidx.recyclerview.widget.RecyclerView
import fastcampus.part3.chapter3.databinding.ItemDetailBinding
import fastcampus.part3.chapter3.model.DetailItem

class DetailViewHolder(private val binding: ItemDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DetailItem) {
        binding.item = item
    }
}