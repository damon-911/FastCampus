package fastcampus.part3.chapter8.presenter.ui.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import fastcampus.part3.chapter8.databinding.ItemContentBinding
import fastcampus.part3.chapter8.domain.model.Content
import fastcampus.part3.chapter8.presenter.ui.MainActivity

class ContentViewHolder(
    private val binding: ItemContentBinding,
    private val handler: MainActivity.Handler
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Content) {
        binding.item = item
        binding.handler = handler
    }
}