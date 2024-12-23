package com.brogrammer.notesbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brogrammer.notesbuddy.ui.screens.FileUploadScreen
import com.brogrammer.notesbuddy.ui.screens.NotesScreen
import com.brogrammer.notesbuddy.ui.screens.QnAScreen
import com.brogrammer.notesbuddy.ui.theme.NotesBuddyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes Buddy") }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "file_upload",
            modifier = Modifier.padding(it)
        ) {
            composable("file_upload") { FileUploadScreen(navController) }
            composable("qna") { QnAScreen(navController) }
            composable("notes") { NotesScreen(navController) }
        }
    }
}