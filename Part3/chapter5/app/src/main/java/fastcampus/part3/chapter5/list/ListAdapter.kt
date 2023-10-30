package fastcampus.part3.chapter5.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part3.chapter5.databinding.ItemImageBinding
import fastcampus.part3.chapter5.databinding.ItemVideoBinding
import fastcampus.part3.chapter5.list.viewholder.ImageItemViewHolder
import fastcampus.part3.chapter5.list.viewholder.VideoItemViewHolder
import fastcampus.part3.chapter5.model.ImageItem
import fastcampus.part3.chapter5.model.ListItem

class ListAdapter(private val itemHandler: ItemHandler? = null) :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(diffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is ImageItem) {
            IMAGE
        } else {
            VIDEO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == IMAGE) {
            ImageItemViewHolder(ItemImageBinding.inflate(inflater, parent, false), itemHandler)
        } else {
            VideoItemViewHolder(ItemVideoBinding.inflate(inflater, parent, false), itemHandler)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (getItemViewType(position) == IMAGE) {
            (holder as ImageItemViewHolder).bind(item)
        } else {
            (holder as VideoItemViewHolder).bind(item)
        }
    }

    companion object {
        private const val IMAGE = 0
        private const val VIDEO = 1

        private val diffUtil = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) =
                oldItem.thumbnailUrl == newItem.thumbnailUrl

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) =
                oldItem == newItem
        }
    }
}