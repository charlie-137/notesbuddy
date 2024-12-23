package com.brogrammer.notesbuddy.ui.screens

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FileUploadScreen(navController: NavController) {
    var selectedFileName by remember { mutableStateOf("No file selected") }
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    // File Picker Launcher
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            selectedFileName = getFileName(uri, context.contentResolver)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Upload File", style = MaterialTheme.typography.headlineSmall)
        Button(onClick = {
            // TODO: Implement file picker logic
            filePickerLauncher.launch("application/pdf")
//            selectedFile = "Example.pdf"
        }) {
            Text("Select File")
        }
        Text("Selected File: $selectedFileName", style = MaterialTheme.typography.bodyMedium)
        Button(
            onClick = {
                selectedFileUri?.let {
                    navController.navigate("qna")
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = selectedFileUri != null
        ) {
            Text("Proceed to Q&A")
        }
    }
}

// Get file name from the URI.

fun getFileName(uri: Uri, contentResolver: ContentResolver): String {
    var fileName = "Unknown"
//    val contentResolver = LocalContext.current.contentResolver
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (cursor.moveToFirst() && nameIndex >= 0) {
            fileName = cursor.getString(nameIndex)
        }
    }
    return fileName
}
