package com.roy.testmozper.repository

import com.roy.testmozper.db.dao.UserDao
import com.roy.testmozper.db.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val localDataSource: UserDao,
) {
    suspend fun insert(user: User) = localDataSource.insert(user)

    fun getAllsers() = localDataSource.getAllUsers()

    suspend fun deleteAll() = localDataSource.deleteAll()
}