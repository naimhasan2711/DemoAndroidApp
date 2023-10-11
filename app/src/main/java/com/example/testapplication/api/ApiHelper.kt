package com.example.testapplication.api

import com.example.testapplication.models.NewsResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getArticle():Response<NewsResponse>
}