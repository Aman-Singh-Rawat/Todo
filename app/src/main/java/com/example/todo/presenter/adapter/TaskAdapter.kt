package com.example.todo.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.model.Tasks
import com.example.todo.databinding.TasksItemBinding

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var taskList: MutableList<Tasks> = mutableListOf()
    inner class TaskViewHolder(val binding: TasksItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TasksItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)

        return TaskViewHolder(binding)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.binding.tvTaskName.text = taskList[position].taskName
        holder.binding.tvTaskTime.text = taskList[position].taskTime
        holder.binding.imgChecked.setOnClickListener {
            holder.binding.imgChecked.isSelected = !holder.binding.imgChecked.isSelected
        }
    }

    fun updateUi(taskList: MutableList<Tasks>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }
}