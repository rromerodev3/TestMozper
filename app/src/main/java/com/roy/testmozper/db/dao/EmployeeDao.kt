package com.roy.testmozper.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roy.testmozper.db.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employees: List<Employee>)

    @Query("SELECT * FROM employee")
    fun getAllEmployees(): Flow<List<Employee>>

    @Query("DELETE FROM employee")
    suspend fun deleteAllEmployees()
}