package com.roy.testmozper.viewmodel

import androidx.lifecycle.*
import com.roy.testmozper.db.model.Employee
import com.roy.testmozper.repository.EmployeeRepository
import com.roy.testmozper.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val userRepository: UserRepository,
        private val employeeRepository: EmployeeRepository
) : ViewModel() {

    val loginState =
            userRepository.getAllsers().map { it.isNotEmpty() }.asLiveData()

    private val _title = MutableLiveData<Int?>(null)
    val title: LiveData<Int?> get() = _title

    private val _selectedEmployee = MutableLiveData<Employee>()
    val selectedEmployee: LiveData<Employee> get() = _selectedEmployee

    fun setTitle(resourceTitle: Int) {
        _title.value = resourceTitle
    }

    fun setSelectedEmployee(employee: Employee) {
        _selectedEmployee.value = employee
    }

    fun doLogout() = viewModelScope.launch {
        userRepository.deleteAll()
        employeeRepository.deleteAllEmployees()
    }
}