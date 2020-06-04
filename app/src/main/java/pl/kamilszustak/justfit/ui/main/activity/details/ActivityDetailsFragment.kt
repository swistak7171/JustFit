package pl.kamilszustak.justfit.ui.main.activity.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentActivityDetailsBinding
import pl.kamilszustak.justfit.domain.item.ActivityEquipmentItem
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.updateModels
import timber.log.Timber
import javax.inject.Inject

class ActivityDetailsFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ActivityDetailsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentActivityDetailsBinding
    private val args: ActivityDetailsFragmentArgs by navArgs()
    private val modelAdapter: ModelAdapter<Equipment, ActivityEquipmentItem> by lazy {
        ModelAdapter<Equipment, ActivityEquipmentItem> { equipment ->
            ActivityEquipmentItem(equipment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentActivityDetailsBinding>(
            inflater,
            R.layout.fragment_activity_details,
            container,
            false
        ).apply {
            this.viewModel = this@ActivityDetailsFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_activity, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initializeRecyclerView()
        setListeners()
        observeViewModel()
        viewModel.loadData(args.activityId)
    }

    private fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter)

        binding.equipmentRecyclerView.apply {
            this.adapter = fastAdapter
        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.activityResource.data.observe(viewLifecycleOwner) { activity ->
            modelAdapter.updateModels(activity.usedEquipment)
            Timber.i(activity.usedEquipment.toString())
        }
    }
}