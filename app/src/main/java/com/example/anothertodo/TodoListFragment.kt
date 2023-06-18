package com.example.anothertodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoListFragment : Fragment() {
    private lateinit var todoListAdapter: TodoListAdapter
    private val todoItemsRepository = TodoItemsRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)
        val rvTodoList: RecyclerView = view.findViewById(R.id.rvTodoList)

        val todoItems = todoItemsRepository.getTodoItems()
        todoListAdapter = TodoListAdapter(todoItems)

        rvTodoList.adapter = todoListAdapter
        rvTodoList.layoutManager = LinearLayoutManager(requireContext())

        val fabAddTodo: FloatingActionButton = view.findViewById(R.id.fabAddTodo)
        fabAddTodo.setOnClickListener {
            findNavController().navigate(R.id.toTodoEditFragment)
//            val fragment = TodoEditFragment(todoItemsRepository)
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, fragment)
//                .addToBackStack(null)
//                .commit()
        }

        return view
    }
}