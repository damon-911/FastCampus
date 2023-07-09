package fastcampus.part2.chapter10.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.part2.chapter10.R
import fastcampus.part2.chapter10.databinding.ItemArticleBinding

class HomeArticleAdapter(
    val onItemClicked: (ArticleItem) -> Unit,
    val onBookmarkClicked: (String, Boolean) -> Unit
) : ListAdapter<ArticleItem, HomeArticleAdapter.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ArticleItem>() {
            override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem.articleId == newItem.articleId
            }

            override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(articleItem: ArticleItem) {
            binding.tvDescription.text = articleItem.description

            Glide.with(binding.ivThumbnail)
                .load(articleItem.imageUrl)
                .into(binding.ivThumbnail)

            binding.root.setOnClickListener {
                onItemClicked(articleItem)
            }

            if (articleItem.isBookMark) {
                binding.ibBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
            } else {
                binding.ibBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
            }

            binding.ibBookmark.setOnClickListener {
                onBookmarkClicked.invoke(articleItem.articleId, articleItem.isBookMark.not())

                articleItem.isBookMark = articleItem.isBookMark.not()

                if (articleItem.isBookMark) {
                    binding.ibBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
                } else {
                    binding.ibBookmark.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticleBinding.inflate(
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