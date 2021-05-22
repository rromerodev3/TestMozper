package com.roy.testmozper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roy.testmozper.db.model.Employee

class DetailViewModel: ViewModel() {

    private val _employee = MutableLiveData<Employee>()
    val employee get() = _employee

    private val _selectedEmployee = MutableLiveData<Employee>()
    val selectedEmployee: LiveData<Employee> get() = _selectedEmployee

    fun setEmploye(employee: Employee) {
        _employee.value = employee
    }
}