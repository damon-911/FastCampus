package fastcampus.part3.chapter7.adapter

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part3.chapter7.MainActivity
import fastcampus.part3.chapter7.databinding.ItemContentBinding
import fastcampus.part3.chapter7.model.ContentEntity

class ContentViewHolder(
    private val binding: ItemContentBinding,
    private val handler: MainActivity.Handler? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ContentEntity) {
        binding.item = item
        binding.handler = handler

        binding.contentCheckBox.paintFlags = if (item.isDone) {
            binding.contentCheckBox.paintFlags + Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            0
        }
    }
}