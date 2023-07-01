package com.example.anothertodo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.Date

object TodoItemsRepository {
    private val todoItemsList: MutableList<TodoItem> = mutableListOf()

    init {
        todoItemsList.add(
            TodoItem(
                "100",
                "Сделать покупки",
                Priority.HIGH,
                null,
                false,
                Date(),
                null
            )
        )
        todoItemsList.add(
            TodoItem(
                "200",
                "Выкинуть мусор",
                Priority.COMMON,
                null,
                true,
                Date(),
                null
            )
        )
        todoItemsList.add(
            TodoItem(
                "300",
                "Пропылесосить",
                Priority.LOW,
                null,
                false,
                Date(),
                Date()
            )
        )
        for (i in 1..6) {
            todoItemsList.add(
                TodoItem(
                    i.toString(),
                    "Сделать лабораторную номер $i",
                    Priority.COMMON,
                    null,
                    false,
                    Date(),
                    null
                )
            )
        }
        for (i in 1..6) {
            todoItemsList.add(
                TodoItem(
                    (i + 10).toString(),
                    "Сделать домашку номер $i",
                    Priority.COMMON,
                    null,
                    false,
                    Date(),
                    null
                )
            )
        }
    }

    fun getSelf(): TodoItemsRepository {
        return this
    }

    fun getTodoItems(): List<TodoItem> {
        return todoItemsList
    }

    fun getLastItem(): TodoItem {
        return todoItemsList[todoItemsList.size - 1]
    }

    fun addTodoItem(todoItem: TodoItem) {
        todoItemsList.add(todoItem)
    }

    fun deleteTodoItem(todoItem: TodoItem){
        todoItemsList.remove(todoItem)
    }

    fun changeTodoItem(todoItem: TodoItem){
        val oldItem = todoItemsList.filter { it.id == todoItem.id }[0]
        deleteTodoItem(oldItem)
        addTodoItem(todoItem)
    }

    fun changeIsCompleted(todoItem: TodoItem){
        deleteTodoItem(todoItem)
        addTodoItem(
            TodoItem(todoItem.id, todoItem.text, todoItem.priority, todoItem.deadline,
        !todoItem.isCompleted, todoItem.createdAt, todoItem.modifiedAt)
        )
    }
}