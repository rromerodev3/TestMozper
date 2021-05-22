package com.roy.testmozper.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.roy.testmozper.R
import com.roy.testmozper.databinding.FragmentHomeBinding
import com.roy.testmozper.db.model.Employee
import com.roy.testmozper.viewmodel.HomeViewModel
import com.roy.testmozper.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment(), EmployeesAdapter.Listener {

    private val ownViewModel by viewModels<HomeViewModel>()
    private val parentViewModel by activityViewModels<MainViewModel>()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = ownViewModel

        binding.recyclerViewEmployes.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EmployeesAdapter(this@HomeFragment)
        }

        setupObserves()
        ownViewModel.getEmployes()
    }

    private fun setupObserves() {
        ownViewModel.employes.observe(viewLifecycleOwner, { employess ->
            if (employess.isNotEmpty()) {
                (binding.recyclerViewEmployes.adapter as EmployeesAdapter).setEmployees(employess)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        parentViewModel.setTitle(R.string.string_home)
    }

    override fun onClickElement(employee: Employee) {
        parentViewModel.setSelectedEmployee(employee)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_logout -> {
                parentViewModel.doLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}