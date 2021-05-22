package com.roy.testmozper

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.roy.testmozper.db.AppDatabase
import com.roy.testmozper.db.dao.EmployeeDao
import com.roy.testmozper.db.dao.UserDao
import com.roy.testmozper.db.model.Employee
import com.roy.testmozper.db.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MozperTests {

    private lateinit var db: AppDatabase

    private lateinit var userDao: UserDao
    private lateinit var employeeDao: EmployeeDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()

        userDao = db.userDao()
        employeeDao = db.employeeDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadUser() = runBlocking {

        val newUser = User(0, "email@example.com")
        userDao.insert(newUser)

        val lastUser = userDao.getAllUsers().first().first()

        Assert.assertNotNull(lastUser)
        Assert.assertEquals(lastUser.email, newUser.email)
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadEmployee() = runBlocking {

        val newEmployee = Employee(1, "Jhon", "Doe", "", "", 0.0)
        employeeDao.insert(listOf(newEmployee))

        val lastEmployee = employeeDao.getAllEmployees().first().first()

        Assert.assertNotNull(lastEmployee)
        Assert.assertEquals(lastEmployee.firstName, newEmployee.firstName)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}