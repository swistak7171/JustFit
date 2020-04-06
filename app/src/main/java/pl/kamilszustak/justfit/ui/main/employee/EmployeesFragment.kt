package pl.kamilszustak.justfit.ui.main.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mikepenz.fastadapter.ClickListener
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentEmployeesBinding
import pl.kamilszustak.justfit.domain.item.EmployeeItem
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.updateModels
import javax.inject.Inject

class EmployeesFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EmployeesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentEmployeesBinding
    private val modelAdapter: ModelAdapter<Employee, EmployeeItem> by lazy {
        ModelAdapter<Employee, EmployeeItem> { employee ->
            EmployeeItem(employee)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEmployeesBinding>(
            inflater,
            R.layout.fragment_employees,
            container,
            false
        ).apply {
            this.viewModel = this@EmployeesFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        setListeners()
        observeViewModel()
    }

    private fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            this.onClickListener = object : ClickListener<EmployeeItem> {
                override fun invoke(
                    v: View?,
                    adapter: IAdapter<EmployeeItem>,
                    item: EmployeeItem,
                    position: Int
                ): Boolean {
                    navigateToEmployeeDetailsFragment(item.model.id)
                    return true
                }
            }
        }

        binding.employeesRecyclerView.apply {
            this.adapter = fastAdapter
        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.employeesResource.data.observe(viewLifecycleOwner) { employees ->
            modelAdapter.updateModels(employees)
        }
    }

    private fun navigateToEmployeeDetailsFragment(employeeId: Long) {

    }
}