package fastcampus.part2.chapter6.chatlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part2.chapter6.databinding.ItemChatroomBinding

class ChatListAdapter(private val onClick: (ChatRoomItem) -> Unit) : ListAdapter<ChatRoomItem, ChatListAdapter.ViewHolder>(differ) {

    companion object {
        val differ = object : DiffUtil.ItemCallback<ChatRoomItem>() {
            override fun areItemsTheSame(oldItem: ChatRoomItem, newItem: ChatRoomItem): Boolean {
                return oldItem.chatRoomId == newItem.chatRoomId
            }

            override fun areContentsTheSame(oldItem: ChatRoomItem, newItem: ChatRoomItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemChatroomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ChatRoomItem) {
            binding.tvNickname.text = item.otherUserName
            binding.tvLastMessage.text = item.lastMessage

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChatroomBinding.inflate(
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