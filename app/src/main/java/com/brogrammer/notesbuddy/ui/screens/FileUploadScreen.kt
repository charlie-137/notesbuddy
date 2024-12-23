package com.brogrammer.notesbuddy.ui.screens
//
//import android.content.ContentResolver
//import android.net.Uri
//import android.provider.OpenableColumns
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.brogrammer.notesbuddy.service.RetrofitClient
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//
//import android.util.Log
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.platform.LocalContext
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//@Composable
//fun FileUploadScreen(navController: NavController) {
//    var selectedFileName by remember { mutableStateOf("No file selected") }
//    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
//    var uploading by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current
//
//    // File Picker Launcher
//    val filePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            selectedFileUri = uri
//            selectedFileName = getFileName(uri, context.contentResolver)
//        }
//
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text("Upload File", style = MaterialTheme.typography.headlineSmall)
//        Button(onClick = {
//            filePickerLauncher.launch("application/pdf")
////            selectedFile = "Example.pdf"
//        }) {
//            Text("Select File")
//        }
//        Text("Selected File: $selectedFileName", style = MaterialTheme.typography.bodyMedium)
//
//        Button(
//            onClick = {
//                selectedFileUri?.let {
//                    uploading = true
//                    uploadFile(uri)
//                }
//            },
//            modifier = Modifier.align(Alignment.End),
//            enabled = !uploading
//        ) {
//            Text(if (uploading) "Uploading..." else "Upload")
//        }
//
//        Button(
//            onClick = { navController.navigate("qna") },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Text("Procedd to Q&A")
//        }
//    }
//}
//
//// Get file name from the URI.
//
//fun getFileName(uri: Uri, contentResolver: ContentResolver): String {
//    var fileName = "Unknown"
////    val contentResolver = LocalContext.current.contentResolver
//    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
//        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//        if (cursor.moveToFirst() && nameIndex >= 0) {
//            fileName = cursor.getString(nameIndex)
//        }
//    }
//    return fileName
//}
//
//fun uploadFile(uri: Uri) {
//    val context = LocalContext.current
//    val contentResolver = context.contentResolver
//    val inputStream = contentResolver.openInputStream(uri)
//    val fileName = getFileName(uri)
//
//    inputStream?.let { inputStream ->
//        val requestBody = inputStream.readBytes()
//        val filePart = MultipartBody.Part.createFormData(
//            "file", fileName, RequestBody.create("application/pdf".toMediaTypeOrNull(), requestBody)
//        )
//
//        RetrofitClient.apiService.uploadPdf(filePart).enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    Log.d("FileUpload", "File uploaded successfully: ${response.body()?.message}")
//                    // Proceed to the next screen or handle response here
//                } else {
//                    Log.e("FileUpload", "Error uploading file: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                Log.e("FileUpload", "Error: ${t.localizedMessage}")
//            }
//        })
//    }
//}

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.brogrammer.notesbuddy.service.ApiResponse
import com.brogrammer.notesbuddy.service.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//
//@Composable
//fun FileUploadScreen(navController: NavController) {
//    var selectedFileName by remember { mutableStateOf("No file selected") }
//    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
//    var uploading by remember { mutableStateOf(false) }
//
//    val context = LocalContext.current
//
//    // File Picker Launcher
//    val filePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        if (uri != null) {
//            selectedFileUri = uri
//            selectedFileName = getFileName(uri, context.contentResolver)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text("Upload File", style = MaterialTheme.typography.headlineSmall)
//
//        Button(onClick = {
//            filePickerLauncher.launch("application/pdf")
//        }) {
//            Text("Select File")
//        }
//
//        Text("Selected File: $selectedFileName", style = MaterialTheme.typography.bodyMedium)
//
//        Button(
//            onClick = {
//                selectedFileUri?.let { uri ->
//                    // Start uploading the file
//                    uploading = true
//                    uploadFile(uri, context.contentResolver)
//                }
//            },
//            enabled = !uploading
//        ) {
//            Text(if (uploading) "Uploading..." else "Upload")
//        }
//
//        Button(
//            onClick = { navController.navigate("qna") },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Text("Proceed to Q&A")
//        }
//    }
//}
//
//fun getFileName(uri: Uri, contentResolver: ContentResolver): String {
//    var fileName = "Unknown"
//    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
//        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//        if (cursor.moveToFirst() && nameIndex >= 0) {
//            fileName = cursor.getString(nameIndex)
//        }
//    }
//    return fileName
//}
//
//fun uploadFile(uri: Uri, contentResolver: ContentResolver) {
//    val inputStream = contentResolver.openInputStream(uri)
//    val fileName = getFileName(uri, contentResolver)
//
//    inputStream?.let { inputStream ->
//        val requestBody = inputStream.readBytes()
//        val filePart = MultipartBody.Part.createFormData(
//            "file", fileName, RequestBody.create("application/pdf".toMediaTypeOrNull(), requestBody)
//        )
//
//        RetrofitClient.apiService.uploadPdf(filePart).enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
//                if (response.isSuccessful) {
//                    Log.d("FileUpload", "File uploaded successfully: ${response.body()?.message}")
//                    // Proceed to the next screen or handle response here
//                } else {
//                    Log.e("FileUpload", "Error uploading file: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                Log.e("FileUploadFailure", "Error: ${t.localizedMessage}")
//            }
//        })
//    }
//}

@Composable
fun FileUploadScreen(navController: NavController) {
    var selectedFileName by remember { mutableStateOf("No file selected") }
    var selectedFileUri by remember { mutableStateOf<Uri?>(null) }
    var uploading by remember { mutableStateOf(false) }

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
            filePickerLauncher.launch("application/pdf")
        }) {
            Text("Select File")
        }

        Text("Selected File: $selectedFileName", style = MaterialTheme.typography.bodyMedium)

        Button(
            onClick = {
                selectedFileUri?.let { uri ->
                    // Start uploading the file
                    uploading = true
                    uploadFile(uri, context.contentResolver, context) {
                        uploading = false // Reset the state after completion
                    }
                }
            },
            enabled = !uploading
        ) {
            Text("Upload")
        }

        Button(
            onClick = { navController.navigate("qna") },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Proceed to Q&A")
        }
    }
}

fun getFileName(uri: Uri, contentResolver: ContentResolver): String {
    var fileName = "Unknown"
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (cursor.moveToFirst() && nameIndex >= 0) {
            fileName = cursor.getString(nameIndex)
        }
    }
    return fileName
}

fun uploadFile(uri: Uri, contentResolver: ContentResolver, context: Context, onComplete: () -> Unit) {
    val inputStream = contentResolver.openInputStream(uri)
    val fileName = getFileName(uri, contentResolver)

    inputStream?.let { inputStream ->
        val requestBody = inputStream.readBytes()
        val filePart = MultipartBody.Part.createFormData(
            "file", fileName, RequestBody.create("application/pdf".toMediaTypeOrNull(), requestBody)
        )

        RetrofitClient.apiService.uploadPdf(filePart).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    Log.d("FileUpload", "File uploaded successfully: ${response.body()?.message}")
                    Toast.makeText(context, "File uploaded successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e("FileUpload", "Error uploading file: ${response.message()}")
                    Toast.makeText(context, "Error uploading file: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
                onComplete() // Reset the state
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("FileUploadFailure", "Error: ${t.localizedMessage}")
                Toast.makeText(context, "Upload failed: ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                onComplete() // Reset the state
            }
        })
    } ?: onComplete() // Reset the state if inputStream is null
}
