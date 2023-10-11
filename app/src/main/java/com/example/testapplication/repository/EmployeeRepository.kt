package com.example.testapplication.repository

import com.example.testapplication.api.ApiHelper
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getEmployee() = apiHelper.getEmployees()
}