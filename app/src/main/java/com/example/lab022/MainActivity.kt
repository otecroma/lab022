package com.example.lab022

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab022.ui.theme.Lab022Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab022Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TodoApp(modifier: Modifier = Modifier) {
    var taskText by remember { mutableStateOf("") }
    var taskList by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Поле вводу завдання
        BasicTextField(
            value = taskText,
            onValueChange = { taskText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    if (taskText.isEmpty()) {
                        Text("Введіть завдання", style = MaterialTheme.typography.bodyLarge)
                    }
                    innerTextField()
                }
            }
        )

        // Кнопка додавання завдання
        Button(
            onClick = {
                if (taskText.isNotBlank()) {
                    taskList = taskList + taskText
                    taskText = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Додати завдання")
        }

        // Заголовок списку завдань
        Text(
            text = "Список завдань:",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Список завдань
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(taskList.size) { index ->
                Text(
                    text = "${index + 1}. ${taskList[index]}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    Lab022Theme {
        TodoApp()
    }
}
