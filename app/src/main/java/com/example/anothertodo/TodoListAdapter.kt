package com.example.anothertodo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoListAdapter(private val todoItems: List<TodoItem>) :
    RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = todoItems[position]
        holder.bind(todoItem)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)
        private val tvTodoText: TextView = itemView.findViewById(R.id.tvTodoText)

        fun bind(todoItem: TodoItem) {
            if (todoItem.isCompleted) {
                ivStatus.setImageResource(com.google.android.material.R.drawable.btn_checkbox_checked_mtrl)
            } else {
                ivStatus.setImageResource(com.google.android.material.R.drawable.btn_checkbox_unchecked_mtrl)
            }

            tvTodoText.text = todoItem.text
        }
    }
}