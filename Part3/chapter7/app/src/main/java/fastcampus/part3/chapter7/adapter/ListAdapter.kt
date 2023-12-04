package fastcampus.part3.chapter7.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import fastcampus.part3.chapter7.MainActivity
import fastcampus.part3.chapter7.databinding.ItemContentBinding
import fastcampus.part3.chapter7.model.ContentEntity

class ListAdapter(private val handler: MainActivity.Handler? = null) :
    ListAdapter<ContentEntity, ContentViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            ItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            handler
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ContentEntity>() {
            override fun areItemsTheSame(oldItem: ContentEntity, newItem: ContentEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ContentEntity, newItem: ContentEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}