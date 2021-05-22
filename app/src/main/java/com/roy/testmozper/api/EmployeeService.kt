package com.roy.testmozper.api

import com.roy.testmozper.dto.DtoEmployees
import retrofit2.http.GET

interface EmployeeService {
    @GET("/")
    suspend fun getEmployees(): DtoEmployees
}