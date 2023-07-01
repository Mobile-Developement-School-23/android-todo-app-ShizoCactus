package com.example.anothertodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodoListFragment : Fragment() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://beta.mrdekk.ru/todobackend")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    private val taskRepository = TaskRepository(apiService)
    private val taskViewModel = TaskViewModel(taskRepository)


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
//        val todoItems = taskViewModel.tasks.value
        todoListAdapter = TodoListAdapter(todoItems)

        rvTodoList.adapter = todoListAdapter
        rvTodoList.layoutManager = LinearLayoutManager(requireContext())

        val fabAddTodo: FloatingActionButton = view.findViewById(R.id.fabAddTodo)
        fabAddTodo.setOnClickListener {
            findNavController().navigate(R.id.toTodoEditFragment)
        }

        lifecycleScope.launch {
            taskViewModel.events.collectLatest { event ->
                when(event) {
                    TaskEvent.SUCCESS -> {

                    }
                    TaskEvent.LOADING -> {

                    }
                    TaskEvent.ERROR -> {

                    }
                }

            }
        }

        return view
    }
}