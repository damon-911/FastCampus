package fastcampus.part3.chapter6.viewholder

import fastcampus.part3.chapter6.adapter.ListAdapter
import fastcampus.part3.chapter6.databinding.ItemViewPagerBinding
import fastcampus.part3.chapter6.model.ListItem
import fastcampus.part3.chapter6.model.ViewPager

class ViewPagerViewHolder(
    binding: ItemViewPagerBinding,
) : BindingViewHolder<ItemViewPagerBinding>(binding) {

    private val adapter = ListAdapter()

    init {
        binding.viewpager.adapter = adapter
    }

    override fun bind(item: ListItem) {
        super.bind(item)
        item as ViewPager
        adapter.submitList(item.items)
    }
}