package com.example.todolistdigital.view.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistdigital.model.Task

class BindingAdapter {

    companion object{
        @JvmStatic
        @BindingAdapter("taskRecycler")
        fun bindTaskRecycler(recyclerView: RecyclerView, list: List<Task>?){
            if (list != null) {
                val adapter = recyclerView.adapter as TaskListAdapter
                adapter.addAll(list as ArrayList<Task>)
            }
        }
    }
}