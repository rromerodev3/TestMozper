package com.roy.testmozper.repository

import com.roy.testmozper.api.EmployeeService
import com.roy.testmozper.db.dao.EmployeeDao
import com.roy.testmozper.db.model.Employee
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val localDataSource: EmployeeDao,
    private val remoteDataSource: EmployeeService
) {
    suspend fun getRemoteEmployees() = remoteDataSource.getEmployees()

    fun getLocalEmployees() = localDataSource.getAllEmployees()

    suspend fun saveEmployees(employees: List<Employee>) = localDataSource.insert(employees)

    suspend fun deleteAllEmployees() = localDataSource.deleteAllEmployees()
}