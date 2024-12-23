package com.brogrammer.notesbuddy.service

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

data class ApiResponse(val message: String)
data class SearchQuery(val query: String)
data class SearchResponse(val results: List<SearchResult>)
data class SearchResult(val sentence: String, val score: Float)
interface ApiService {

    @Multipart
    @POST("/upload_pdf/")
    fun uploadPdf(@Part file: MultipartBody.Part): Call<ApiResponse>

    @POST("/search/")
    fun search(@Body query: SearchQuery): Call<SearchResponse>
}
