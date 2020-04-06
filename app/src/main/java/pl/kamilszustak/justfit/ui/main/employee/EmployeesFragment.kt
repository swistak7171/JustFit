package pl.kamilszustak.justfit.ui.main.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentEmployeesBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class EmployeesFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EmployeesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentEmployeesBinding

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
        observeViewModel()
    }

    private fun initializeRecyclerView() {

    }

    private fun observeViewModel() {

    }
}