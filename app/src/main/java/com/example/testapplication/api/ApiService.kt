package com.example.testapplication.api

import com.example.testapplication.models.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("employees")
    suspend fun getEmployees(): Response<EmployeeResponse>


}