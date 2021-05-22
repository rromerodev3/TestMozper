package com.roy.testmozper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roy.testmozper.databinding.EmployeeItemBinding
import com.roy.testmozper.db.model.Employee

class EmployeesAdapter(private val listener: Listener): RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>() {

    interface Listener {
        fun onClickElement(employee: Employee)
    }

    private var employees = listOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = EmployeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]

        holder.binding.employee = employee

        holder.binding.root.setOnClickListener {
            listener.onClickElement(employee)
        }
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun setEmployees(newEmployees: List<Employee>) {
        employees = newEmployees
        notifyDataSetChanged()
    }

    class EmployeeViewHolder(val binding: EmployeeItemBinding): RecyclerView.ViewHolder(binding.root) {}
}