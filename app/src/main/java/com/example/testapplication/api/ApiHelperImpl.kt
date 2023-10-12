package com.example.testapplication.api

import com.example.testapplication.models.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{
    override suspend fun getArticle(): Response<NewsResponse> = apiService.getBreakingNews()
}