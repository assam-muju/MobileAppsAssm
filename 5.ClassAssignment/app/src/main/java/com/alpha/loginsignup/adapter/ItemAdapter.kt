package com.alpha.loginsignup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpha.loginsignup.databinding.ListItemBinding
import com.alpha.loginsignup.model.Item
import com.alpha.loginsignup.model.ItemAction

class ItemAdapter(
    private val items: List<Item>,
    private val onAction: (Item, ItemAction) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onAction)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ItemViewHolder(
        private val binding: ListItemBinding,
        private val onAction: (Item, ItemAction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.imageView.setImageResource(item.imageRes)
            binding.textViewTitle.text = item.title

            binding.root.setOnClickListener {
                onAction(item, ItemAction.CLICK)
            }

            binding.btnLike.setOnClickListener {
                onAction(item, ItemAction.BUTTON_CLICK)
            }
        }
    }
}
