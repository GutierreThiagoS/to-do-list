package com.example.todolistdigital.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.todolistdigital.databinding.ActivityAddTaskBinding
import com.example.todolistdigital.extensions.TwoDigits
import com.example.todolistdigital.extensions.format
import com.example.todolistdigital.extensions.text
import com.example.todolistdigital.model.Task
import com.example.todolistdigital.viewmodel.TaskViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import java.util.*

class AddTaskActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityAddTaskBinding

    private val viewModel: TaskViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(TASK_ID)){
            val taskId = intent.getIntExtra(TASK_ID, 0)
            lifecycleScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }) {
                val task = viewModel.getTask(taskId)
                withContext(Dispatchers.Main){
                    task.let {
                        binding.tilTitle.text = it?.title ?: ""
                        binding.tilDate.text = it?.date  ?: ""
                        binding.tilHour.text = it?.hour  ?: ""
                    }
                }
            }

        }
        insertListeners()
    }

    private fun insertListeners(){
        binding.tilDate.editText?.setOnClickListener {
            val  datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHour.editText?.setOnClickListener {
            val timerPicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timerPicker.addOnPositiveButtonClickListener {
                binding.tilHour.text = "${timerPicker.hour.TwoDigits()}:${timerPicker.minute.TwoDigits()}"
            }
            timerPicker.show(supportFragmentManager, null)
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnCreateTask.setOnClickListener {
            val task = Task(
                title = binding.tilTitle.text,
                hour = binding.tilHour.text,
                date = binding.tilDate.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            lifecycleScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }){
                viewModel.insertOrUpdateTask(task)
            }
            finish()
        }
    }

    companion object{
        const val TASK_ID = "task_id"
    }

}