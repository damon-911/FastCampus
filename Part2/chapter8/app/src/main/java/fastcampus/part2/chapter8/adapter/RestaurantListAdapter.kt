package fastcampus.part2.chapter8.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import fastcampus.part2.chapter8.databinding.ItemRestaurantBinding
import fastcampus.part2.chapter8.search.SearchItem

class RestaurantListAdapter(private val onClick: (LatLng) -> Unit) :
    RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    private var dataSet = emptyList<SearchItem>()

    inner class ViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchItem) {
            binding.tvTitle.text = item.title
            binding.tvCategory.text = item.category
            binding.tvLocation.text = item.roadAddress

            binding.root.setOnClickListener {
                onClick(Tm128(item.mapx.toDouble(), item.mapy.toDouble()).toLatLng())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataSet: List<SearchItem>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}