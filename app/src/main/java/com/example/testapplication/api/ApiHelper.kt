package com.example.testapplication.api

import com.example.testapplication.models.EmployeeResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getEmployees(): Response<EmployeeResponse>

}