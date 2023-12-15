package fastcampus.part3.chapter8.presenter.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import fastcampus.part3.chapter8.databinding.ItemContentBinding
import fastcampus.part3.chapter8.domain.model.Content
import fastcampus.part3.chapter8.presenter.ui.MainActivity
import fastcampus.part3.chapter8.presenter.ui.list.viewholder.ContentViewHolder

class ListAdapter(private val handler: MainActivity.Handler) :
    androidx.recyclerview.widget.ListAdapter<Content, ContentViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            handler
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Content>() {
            override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
                return oldItem == newItem
            }
        }
    }
}