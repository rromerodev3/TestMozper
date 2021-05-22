package com.roy.testmozper.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.roy.testmozper.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val employeeRepository: EmployeeRepository
) : ViewModel() {

    val employes = employeeRepository.getLocalEmployees().asLiveData()
    val hasData = employeeRepository.getLocalEmployees().map { it.isNotEmpty() } .asLiveData()

    val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getEmployes() {
        _isLoading.value = true

        viewModelScope.launch {

            try {

                val dtoEmployees = employeeRepository.getRemoteEmployees()
                if (dtoEmployees.employees.isNotEmpty()) {
                    employeeRepository.deleteAllEmployees()
                    employeeRepository.saveEmployees(dtoEmployees.employees)
                }

            } catch (exception: Exception) {
                Log.e("testmozper", "getEmployes: ${exception.message}", exception)
            }

            delay(1000)
            _isLoading.postValue(false)
        }
    }
}
