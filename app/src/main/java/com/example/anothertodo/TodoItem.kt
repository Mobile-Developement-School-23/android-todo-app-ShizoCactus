package com.example.anothertodo

import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val priority: Priority,
    val deadline: Date?,
    var isCompleted: Boolean,
    val createdAt: Date,
    val modifiedAt: Date?,
) {
    enum class Priority {
        LOW,
        COMMON,
        HIGH,
    }
}