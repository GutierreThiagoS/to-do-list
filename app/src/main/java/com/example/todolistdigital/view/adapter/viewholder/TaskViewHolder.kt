package com.example.todolistdigital.view.adapter.viewholder

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistdigital.R
import com.example.todolistdigital.databinding.ItemTaskBinding
import com.example.todolistdigital.model.Task

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    var listenerEdit: (Task) -> Unit = {},
    var listenerDelete: (Task) -> Unit = {}
): RecyclerView.ViewHolder(binding.root){
    fun bind(item: Task){
        binding.tvTitle.text = item.title
        binding.tvDate.text = "${item.date} ${item.hour}"
        binding.ivMore.setOnClickListener {
            showPopup(item)
        }
    }

    private fun showPopup(item: Task){
        val ivMore = binding.ivMore
        val popupMenu = PopupMenu(ivMore.context, ivMore)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.action_edit -> listenerEdit(item)
                R.id.action_excluir -> listenerDelete(item)
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }
}