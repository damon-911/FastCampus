package fastcampus.part3.chapter6.viewholder

import fastcampus.part3.chapter6.adapter.ListAdapter
import fastcampus.part3.chapter6.databinding.ItemHorizontalBinding
import fastcampus.part3.chapter6.model.Horizontal
import fastcampus.part3.chapter6.model.ListItem

class HorizontalViewHolder(
    private val binding: ItemHorizontalBinding,
) : BindingViewHolder<ItemHorizontalBinding>(binding) {

    private val adapter = ListAdapter()

    init {
        binding.listView.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as Horizontal
        binding.titleTextView.text = item.title
        adapter.submitList(item.items)
    }
}