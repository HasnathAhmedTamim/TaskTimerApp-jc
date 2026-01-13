package com.example.tasktimerapp

sealed class Screen(val route: String) {

    data object TaskList : Screen("task_list")

    data object AddTask : Screen("add_task")

    // Example: route with argument
    data object TaskDetail : Screen("task_detail/{taskId}") {
        fun createRoute(taskId: String) = "task_detail/$taskId"
    }
}
