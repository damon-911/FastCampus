package fastcampus.part3.chapter6.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import fastcampus.part3.chapter6.model.ListItem

class DiffCallback<T : ListItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.viewType == newItem.viewType && oldItem.getKey() == newItem.getKey()


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}