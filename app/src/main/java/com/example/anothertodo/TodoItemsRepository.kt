package com.example.anothertodo

object TodoItemsRepository {
    private val todoItemsList: MutableList<TodoItem> = mutableListOf()

    init {
        for (i in 1..5) {
            todoItemsList.add(
                TodoItem(
                    "1",
                    "Сделать покупки",
                    "обычная",
                    null,
                    false,
                    "2023-06-17",
                    null
                )
            )
            todoItemsList.add(
                TodoItem(
                    "2",
                    "Оплатить счета",
                    "срочная",
                    null,
                    false,
                    "2023-06-16",
                    null
                )
            )
        }
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
}