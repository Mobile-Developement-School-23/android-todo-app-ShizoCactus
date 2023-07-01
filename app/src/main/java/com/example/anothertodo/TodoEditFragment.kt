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
import java.util.Calendar
import java.util.Date

class TodoEditFragment() : Fragment() {
    private val itemsRepository = TodoItemsRepository.getSelf()
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
            val year = deadlineDatePicker.year
            val month = deadlineDatePicker.month + 1
            val day = deadlineDatePicker.dayOfMonth
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val deadline = calendar.time
            val priority = when(prioritySpinner.selectedItem){
                "Высокий" -> Priority.HIGH
                "Низкий" -> Priority.LOW
                else -> Priority.COMMON
            }
            itemsRepository.addTodoItem(TodoItem("1", task, priority, deadline, false, Date(), null))

            requireActivity().supportFragmentManager.popBackStack()
        }

        deleteButton.setOnClickListener {
            itemsRepository.deleteTodoItem(itemsRepository.getLastItem())
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
