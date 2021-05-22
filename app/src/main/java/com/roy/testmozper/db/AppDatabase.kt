package com.roy.testmozper.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roy.testmozper.db.dao.EmployeeDao
import com.roy.testmozper.db.dao.UserDao
import com.roy.testmozper.db.model.Employee
import com.roy.testmozper.db.model.User

@Database(entities = [User::class, Employee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun employeeDao(): EmployeeDao
}