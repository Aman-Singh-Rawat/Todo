package com.example.todo.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.PriorityItemBinding

class PriorityAdapter(private val context: Context, private val onClick: OnClickedCatPriority): RecyclerView.Adapter<PriorityAdapter.PriorityViewHolder>() {
    private var itemList: List<String> = listOf()
    private var selectedView: TextView? = null
    inner class PriorityViewHolder(val binding: PriorityItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriorityViewHolder {
        val binding = PriorityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriorityViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: PriorityViewHolder, position: Int) {
        holder.binding.tvPriorityCategory.text = itemList[position]
        if (position == 0) {
            selectedView = holder.binding.tvPriorityCategory
            selectedView?.setTextColor(context.getColor(R.color.white))
            selectedView?.isSelected = true
        }
        holder.itemView.setOnClickListener {
            selectedView?.isSelected = false
            selectedView?.setTextColor(context.getColor(R.color.black))

            selectedView = holder.itemView as TextView
            selectedView?.isSelected = true
            selectedView?.setTextColor(context.getColor(R.color.white))
            onClick.onClicked(selectedView?.text.toString())
        }
    }

    fun updateUi(itemList: List<String>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}

interface OnClickedCatPriority {
    fun onClicked(priorityCat: String)
}