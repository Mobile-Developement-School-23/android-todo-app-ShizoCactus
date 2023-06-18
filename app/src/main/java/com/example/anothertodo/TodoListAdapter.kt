package com.example.anothertodo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
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
        private val tvTodoText: TextView = itemView.findViewById(R.id.tvTodoText)

        fun bind(todoItem: TodoItem) {
            tvTodoText.text = todoItem.text
            val checkBox = itemView.findViewById<CheckBox>(R.id.checked)
            val textView = itemView.findViewById<TextView>(R.id.tvTodoText)
            checkBox.isChecked = todoItem.isCompleted
            if (todoItem.priority == TodoItem.Priority.HIGH){
                textView.setTextColor(Color.RED)
            }
            if (todoItem.priority == TodoItem.Priority.LOW){
                textView.setTextColor(Color.GREEN)
            }
            checkBox.setOnClickListener {
                todoItem.isCompleted = checkBox.isChecked
            }
        }
    }
}