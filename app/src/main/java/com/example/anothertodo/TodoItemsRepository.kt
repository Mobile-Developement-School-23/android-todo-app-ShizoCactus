package com.example.anothertodo

import java.util.Date

object TodoItemsRepository {
    private val todoItemsList: MutableList<TodoItem> = mutableListOf()

    init {
        todoItemsList.add(
            TodoItem(
                "100",
                "Сделать покупки",
                TodoItem.Priority.HIGH,
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
                TodoItem.Priority.COMMON,
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
                TodoItem.Priority.LOW,
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
                    TodoItem.Priority.COMMON,
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
                    TodoItem.Priority.COMMON,
                    null,
                    false,
                    Date(),
                    null
                )
            )
        }
    }

    fun get_self(): TodoItemsRepository {
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