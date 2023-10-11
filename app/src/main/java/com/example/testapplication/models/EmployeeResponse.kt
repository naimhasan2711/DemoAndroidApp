package com.example.testapplication.models

data class EmployeeResponse(
    val data: List<Employee>?=null,
    val status: String?=""
)
