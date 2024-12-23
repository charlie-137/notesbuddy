package com.brogrammer.notesbuddy.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun QnAScreen(navController: NavController) {
    var question by remember { mutableStateOf("") }
    var answer by remember { mutableStateOf("Answer will be displayed here.") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Ask a Question", style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            value = question,
            onValueChange = { question = it },
            label = { Text("Enter your question") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            // TODO: Implement logic to fetch the answer
            answer = "This is the answer for: $question"
        }) {
            Text("Get Answer")
        }
        Text(answer, style = MaterialTheme.typography.bodyMedium)

        Button(
            onClick = { navController.navigate("notes") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Generate Notes")
        }
    }
}
