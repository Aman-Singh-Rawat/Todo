package com.example.todo.presenter.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todo.presenter.adapter.CategoryAdapter
import com.example.todo.R
import com.example.todo.presenter.adapter.TaskAdapter
import com.example.todo.model.Category
import com.example.todo.model.Tasks
import com.example.todo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val categoryAdapter = CategoryAdapter()
    private val tasksAdapter = TaskAdapter()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.updateUi(mutableListOf(
            Category("Project", R.drawable.img_book, 4),
            Category("Work", R.drawable.img_working_time, 5),
            Category("Daily Tasks", R.drawable.img_gym, 6),
            Category("Groceries", R.drawable.img_grocery, 0)
        ))

        binding.rvTasks.adapter = tasksAdapter
        tasksAdapter.updateUi(mutableListOf(
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
            Tasks("Email to Ameer", "08:00 AM to 12:00 PM"),
        ))

        binding.btnCreateNewTask.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                CreateTaskActivity::class.java
            ))
        }
    }
}