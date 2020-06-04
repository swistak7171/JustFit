package pl.kamilszustak.justfit.ui.main.activity.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentClientActivitiesBinding
import pl.kamilszustak.justfit.domain.item.ActivityItem
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.updateModels
import javax.inject.Inject

class ClientActivitiesFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ClientActivitiesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentClientActivitiesBinding
    private val modelAdapter: ModelAdapter<Activity, ActivityItem> by lazy {
        ModelAdapter<Activity, ActivityItem> { activity ->
            ActivityItem(activity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentClientActivitiesBinding>(
            inflater,
            R.layout.fragment_client_activities,
            container,
            false
        ).apply {
            this.viewModel = this@ClientActivitiesFragment.viewModel
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
        val fastAdapter = FastAdapter.with(modelAdapter)

        binding.activitiesRecyclerView.apply {
            this.adapter = fastAdapter
        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.activitiesResource.data.observe(viewLifecycleOwner) { activities ->
            modelAdapter.updateModels(activities)
        }
    }
}