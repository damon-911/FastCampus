package fastcampus.part1.chapter7

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part1.chapter7.databinding.ItemWordBinding

class WordAdapter(
    val list: MutableList<Word>,
    private val itemClickListener: ItemClickListener? = null
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    interface ItemClickListener {
        fun onCLick(item: Word)
    }

    class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word) {
            binding.apply {
                tvItem.text = item.word
                tvItemMean.text = item.mean
                typeChip.text = item.type
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding =  ItemWordBinding.inflate(inflater, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { itemClickListener?.onCLick(list[position]) }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}