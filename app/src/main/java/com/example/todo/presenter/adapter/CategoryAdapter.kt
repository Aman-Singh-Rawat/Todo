package com.example.todo.presenter.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.model.Category
import com.example.todo.databinding.CategoryItemBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val colorList = mutableListOf("#bbd0ff", "#f9dcc4", "#9381ff", "#cdb4db")
    private var categoryList: MutableList<Category> = mutableListOf()
    inner class CategoryViewHolder(val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return CategoryViewHolder(binding)
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.run {
            imgCategory.setImageResource(categoryList[position].categoryImage)
            tvProjectName.text = categoryList[position].categoryName
            tvCategoryNumber.text = categoryList[position].categoryNumber.toString()
            cvCategory.setCardBackgroundColor(Color.parseColor(colorList[position]))
        }
    }

    fun updateUi(categoryList: MutableList<Category>) {
        this.categoryList = categoryList
    }
}