package com.example.todolist


data class Todo (val title: String, val date: String, val category: String, var isDone: Boolean = false)