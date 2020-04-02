package pl.kamilszustak.justfit.ui.main.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentEquipmentBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class EquipmentFragment : BaseFragment(R.layout.fragment_equipment) {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EquipmentViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentEquipmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEquipmentBinding>(
            inflater,
            R.layout.fragment_equipment,
            container,
            false
        ).apply {
            this.viewModel = this@EquipmentFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        setListeners()
        observeViewModel()
        loadData()
    }

    private fun initializeRecyclerView() {

    }

    private fun setListeners() {
        binding.equipmentFiltersGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            loadData()
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.equipmentResource.data.observe(viewLifecycleOwner) { equipment ->
            Timber.i(equipment.toString())
        }
    }

    private fun loadData() {
        viewModel.loadData(binding.equipmentFiltersGroup.checkedButtonId)
    }
}