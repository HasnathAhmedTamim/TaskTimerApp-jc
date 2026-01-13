package com.example.tasktimerapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tasktimerapp.ui.theme.TaskTimerAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTimerAppRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        when (currentRoute) {
                            Screen.AddTask.route -> "Add Task"
                            else -> "Tasks"
                        }
                    )
                },
                navigationIcon = {
                    if (currentRoute != Screen.TaskList.route) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        TaskNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TaskTimerAppRootPreview() {
    TaskTimerAppTheme {
        TaskTimerAppRoot()
    }
}


@Composable
fun TaskNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TaskList.route,
        modifier = modifier
    ) {
        composable(Screen.TaskList.route) {
            TaskListScreen(
                onAddTaskClick = {
                    navController.navigate(Screen.AddTask.route)
                }
            )
        }
        composable(Screen.AddTask.route) {
            AddTaskScreen(
                onSaveClick = {
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// Placeholder UI screens (will be enhanced later)

@Composable
fun TaskListScreen(
    onAddTaskClick: () -> Unit
) {
    Surface {
        Button(onClick = onAddTaskClick) {
            Text("Go to Add Task")
        }
    }
}

@Composable
fun AddTaskScreen(
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Surface {
        Column {
            Text("Add Task Screen")
            Button(onClick = onSaveClick) { Text("Save") }
            Button(onClick = onCancelClick) { Text("Cancel") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskTimerAppTheme {
        TaskListScreen(
            onAddTaskClick = {}   // খালি lambda
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    TaskTimerAppTheme {
        AddTaskScreen(
            onSaveClick = {},
            onCancelClick = {}
        )
    }
}

