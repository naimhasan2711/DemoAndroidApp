package com.example.testapplication.api

import com.example.testapplication.models.NewsResponse
import com.example.testapplication.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("v2/top-headlines?country=us&category=business&apiKey=64ca4704ac1f4fe5b58a0b5cb785ed3a")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}