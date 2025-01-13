package com.alpha.loginsignup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alpha.loginsignup.databinding.ListItemBinding
import com.alpha.loginsignup.model.Item
import com.alpha.loginsignup.model.ItemAction

class ItemAdapter(
    private val onAction: (Item, ItemAction) -> Unit
) : ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onAction)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(
        private val binding: ListItemBinding,
        private val onAction: (Item, ItemAction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.imageView.setImageResource(item.imageRes)
            binding.textViewTitle.text = item.title
            binding.textViewDes.text = item.description

            binding.root.setOnClickListener {
                onAction(item, ItemAction.CLICK)
            }

            binding.btnLike.setOnClickListener {
                onAction(item, ItemAction.BUTTON_CLICK)
            }
        }
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        // Compare unique identifiers
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        // Compare the contents
        return oldItem == newItem
    }
}
