package com.example.todolistdigital.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.todolistdigital.databinding.ActivityMainBinding
import com.example.todolistdigital.model.Task
import com.example.todolistdigital.view.adapter.TaskListAdapter
import com.example.todolistdigital.viewmodel.TaskViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val taskAdapter by lazy { TaskListAdapter() }

   private val viewModel: TaskViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = taskAdapter

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.taskList.observe(this, {
            updateList(it)
        })

        insertListeners()
    }

    private fun insertListeners(){
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }

        taskAdapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivity(intent)
        }
        taskAdapter.listenerDelete = {
            lifecycleScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }) {
                viewModel.deleteTask(it)
            }
        }
    }

    private fun updateList(list: List<Task>){
        binding.includeEmpty.emptyState.visibility = if (list.isEmpty()) View.VISIBLE
        else View.GONE
    }

}