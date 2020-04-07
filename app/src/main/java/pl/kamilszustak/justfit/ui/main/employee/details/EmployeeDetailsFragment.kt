package pl.kamilszustak.justfit.ui.main.employee.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentEmployeeDetailsBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class EmployeeDetailsFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EmployeeDetailsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentEmployeeDetailsBinding
    private val args: EmployeeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEmployeeDetailsBinding>(
            inflater,
            R.layout.fragment_employee_details,
            container,
            false
        ).apply {
            this.viewModel = this@EmployeeDetailsFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }
}