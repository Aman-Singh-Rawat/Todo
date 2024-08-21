package com.example.todo.presenter.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo.R
import com.example.todo.databinding.ActivityCreateTaskBinding
import com.example.todo.presenter.adapter.PriorityAdapter
import com.example.todo.presenter.adapter.OnClickedCatPriority
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateTaskActivity : AppCompatActivity(), OnClickedCatPriority {
    private lateinit var binding: ActivityCreateTaskBinding
    private val categoryAdapter = PriorityAdapter(this, this)
    private val priorityAdapter = PriorityAdapter(this, this)
    private val calendar = Calendar.getInstance()
    private var mCategory: String = ""
    private var mPriority: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        binding.btnCreateNewTask.setOnClickListener {}
        binding.etDateTime.setOnClickListener { showDatePicker() }
        binding.etStartTime.setOnClickListener { showTimePicker{binding.etStartTime.setText(it)}  }
        binding.etEndDate.setOnClickListener { showTimePicker{binding.etEndDate.setText(it)} }
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.updateUi(listOf("Daily Tasks","Education", "work", "Groceries"))

        binding.rvPriority.adapter = priorityAdapter
        priorityAdapter.updateUi(listOf("Low", "Medium", "High"))
    }

    private fun showTimePicker(onTimeSelected: (String) -> Unit) {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hour, minute ->
                val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
                    .format(Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, hour)
                        set(Calendar.MINUTE, minute)
                    }.time)
                onTimeSelected(formattedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false // 24-hour format
        )
        timePickerDialog.show()
    }

    override fun onClicked(priorityCat: String) {
        when(priorityCat) {
            "Daily Tasks","Education", "work", "Groceries" -> mCategory = priorityCat
            "Low", "Medium", "High" -> mPriority = priorityCat
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
            val formatedDate = dateFormat.format(selectedDate.time)
            binding.etDateTime.setText(formatedDate.toString())
        },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }
}