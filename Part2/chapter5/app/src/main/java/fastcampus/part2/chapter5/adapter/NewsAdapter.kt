package fastcampus.part2.chapter5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.part2.chapter5.databinding.ItemNewsBinding
import fastcampus.part2.chapter5.model.NewsModel

class NewsAdapter(private val onClick: (String) -> Unit): ListAdapter<NewsModel, NewsAdapter.ViewHolder>(
    diffUtil
) {

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsModel) {
            binding.tvTitle.text = item.title
            binding.root.setOnClickListener {
                onClick(item.link)
            }

            Glide.with(binding.ivThumbnail)
                .load(item.imageUrl)
                .into(binding.ivThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}