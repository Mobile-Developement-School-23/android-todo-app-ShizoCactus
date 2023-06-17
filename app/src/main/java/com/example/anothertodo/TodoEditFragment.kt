package com.example.anothertodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner

class TodoEditFragment(val todoItemsRepository: TodoItemsRepository) : Fragment() {
    private val itemsRepository = todoItemsRepository
    private lateinit var taskEditText: EditText
    private lateinit var deadlineDatePicker: DatePicker
    private lateinit var prioritySpinner: Spinner
    private lateinit var closeButton: Button
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_edit, container, false)

        taskEditText = view.findViewById(R.id.taskEditText)
        deadlineDatePicker = view.findViewById(R.id.deadlineDatePicker)
        prioritySpinner = view.findViewById(R.id.prioritySpinner)
        closeButton = view.findViewById(R.id.closeButton)
        saveButton = view.findViewById(R.id.saveButton)
        deleteButton = view.findViewById(R.id.deleteButton)

        closeButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        saveButton.setOnClickListener {
            val task = taskEditText.text.toString()
            val deadline = getSelectedDeadline()
            var priority = prioritySpinner.selectedItem.toString()
            if (priority == null)
                priority = "обычный"
            // Действия по сохранению дела
            itemsRepository.addTodoItem(TodoItem("1", task, priority, deadline, false, "01.01.2000", "01.01.2000"))

            requireActivity().supportFragmentManager.popBackStack()
        }

        deleteButton.setOnClickListener {
            // Действия по удалению дела
            itemsRepository.deleteTodoItem(itemsRepository.getLastItem())
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }

    private fun getSelectedDeadline(): String {
        val year = deadlineDatePicker.year
        val month = deadlineDatePicker.month
        val dayOfMonth = deadlineDatePicker.dayOfMonth

        // Форматирование даты

        return "$dayOfMonth.$month.$year"
    }
}