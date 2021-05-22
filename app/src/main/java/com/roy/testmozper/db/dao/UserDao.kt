package com.roy.testmozper.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roy.testmozper.db.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(users: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}