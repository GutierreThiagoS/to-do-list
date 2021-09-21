package com.example.todolistdigital.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistdigital.databinding.ItemTaskBinding
import com.example.todolistdigital.model.Task
import com.example.todolistdigital.view.adapter.viewholder.TaskViewHolder

class TaskListAdapter: RecyclerView.Adapter<TaskViewHolder>() {

    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}

    private var taskList = ArrayList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding, listenerEdit, listenerDelete)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    fun addAll(list: List<Task>){
        clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        taskList.clear()
        notifyDataSetChanged()
    }

}