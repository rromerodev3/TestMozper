package com.roy.testmozper.di

import android.content.Context
import androidx.room.Room
import com.roy.testmozper.api.EmployeeService
import com.roy.testmozper.constants.Constants
import com.roy.testmozper.db.AppDatabase
import com.roy.testmozper.db.dao.EmployeeDao
import com.roy.testmozper.db.dao.UserDao
import com.roy.testmozper.repository.EmployeeRepository
import com.roy.testmozper.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase = Room.databaseBuilder(appContext, AppDatabase::class.java, "mozper-db").build()

    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Provides
    fun provideEmployeDao(db: AppDatabase) = db.employeeDao()

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideEmployeService(retrofit: Retrofit) = retrofit.create(EmployeeService::class.java)

    @Provides
    fun provideUserRepository(localDataSource: UserDao) = UserRepository(localDataSource)

    @Provides
    fun provideEmployeeRepository(localDataSource: EmployeeDao, remoteDataSource: EmployeeService) =
        EmployeeRepository(localDataSource, remoteDataSource)
}