package com.example.anothertodo

data class TodoItem(
    val id: String,
    val text: String,
    val importance: String,
    val deadline: String?,
    val isCompleted: Boolean,
    val creationDate: String,
    val modificationDate: String?
)