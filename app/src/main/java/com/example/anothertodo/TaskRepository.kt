package com.example.anothertodo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class TaskRepository(private val apiService: ApiService) {

    private val _tasksList = MutableStateFlow<List<TodoItem>>(emptyList())

    val tasksList: Flow<List<TodoItem>>
        get() = _tasksList.asStateFlow()

    private fun showNetworkErrorWhileFetching(e: Exception){
        TODO()
    }

    private fun showNetworkErrorWhileAddingTask(e: Exception){
        TODO()
    }

    private fun showNetworkErrorWhileDeletingTask(e: Exception){
        TODO()
    }

    private fun showNetworkErrorWhileUpdatingTask(e: Exception){
        TODO()
    }

    suspend fun fetchTasks(): List<TodoItem> {
       return withContext(Dispatchers.IO) {
            try {
                val tasks = apiService.fetchTasks()
                _tasksList.value = tasks
                return@withContext tasks
            } catch (e: Exception) {
                throw FetchTasksException()
            }
        }
    }

    suspend fun updateTasksEvery(timeMs: Long = 10000000000L) {
            while(true) {
                val tasks = fetchTasks()
                _tasksList.value = tasks
                delay(timeMs)
            }
    }

    suspend fun addTask(task: TodoItem) {
        try {
            apiService.addTask(task)
        } catch (e: Exception) {
            showNetworkErrorWhileAddingTask(e)
            throw e
        }
    }

    suspend fun deleteTask(taskId: String) {
        try {
            apiService.deleteTask(taskId)
        } catch (e: Exception) {
            showNetworkErrorWhileDeletingTask(e)
            throw e
        }
    }

    suspend fun updateTask(taskId: String, task: TodoItem) {
        try {
            apiService.updateTask(taskId, task)
        } catch (e: Exception) {
            showNetworkErrorWhileUpdatingTask(e)
            throw e
        }
    }
}