package com.example.anothertodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TodoItem>>(emptyList())
    val tasks: StateFlow<List<TodoItem>> get() = _tasks

    private val _events = MutableStateFlow<TaskEvent>(TaskEvent.LOADING)
    val events: StateFlow<TaskEvent>
        get() = _events

    init {
        viewModelScope.launch {
            taskRepository.updateTasksEvery()
        }
        viewModelScope.launch {
            try {
                taskRepository.tasksList
                    .catch {
                        _events.value = TaskEvent.ERROR
                    }
                    .collect { tasks ->
                        _events.value = TaskEvent.SUCCESS
                        _tasks.value = tasks
                    }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showErrorToUser(e)
                }
            }
        }
    }
    private fun showErrorToUser(e: Exception){
        TODO()
    }

    fun fetchData() {
        viewModelScope.launch {
            _events.value = TaskEvent.LOADING
            taskRepository.fetchTasks()
        }
    }
}

enum class TaskEvent {
    SUCCESS,
    ERROR,
    LOADING
}