package pl.kamilszustak.justfit.ui.main.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mikepenz.fastadapter.ClickListener
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.LongClickListener
import com.mikepenz.fastadapter.adapters.ModelAdapter
import org.jetbrains.anko.support.v4.toast
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentActivitiesBinding
import pl.kamilszustak.justfit.domain.item.ActivityItem
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.navigateTo
import pl.kamilszustak.justfit.util.popupMenu
import pl.kamilszustak.justfit.util.updateModels
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

class ActivitiesFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ActivitiesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentActivitiesBinding
    private val modelAdapter: ModelAdapter<Activity, ActivityItem> by lazy {
        ModelAdapter<Activity, ActivityItem> { activity ->
            ActivityItem(activity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_activities_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clientActivitiesItem -> {
                navigateToClientActivitiesFragment()
                true
            }

            R.id.allActivitiesItem -> {
                navigateToAllActivitiesFragment()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentActivitiesBinding>(
            inflater,
            R.layout.fragment_activities,
            container,
            false
        ).apply {
            this.viewModel = this@ActivitiesFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initializeCalendarView()
        initializeRecyclerView()
        setListeners()
        observeViewModel()
    }

    private fun initializeCalendarView() {
        binding.calendarView.apply {
            this.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val date = Date(year - 1900, month, dayOfMonth)
                viewModel.onDateSelected(date)
            }
            this.date = Date().time
        }
    }

    private fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter)
            .apply {
                this.onClickListener = object : ClickListener<ActivityItem> {
                    override fun invoke(
                        v: View?,
                        adapter: IAdapter<ActivityItem>,
                        item: ActivityItem,
                        position: Int
                    ): Boolean {
                        navigateToActivityDetailsFragment(item.model.id)
                        return true
                    }
                }

                this.onLongClickListener = object : LongClickListener<ActivityItem> {
                    override fun invoke(
                        v: View,
                        adapter: IAdapter<ActivityItem>,
                        item: ActivityItem,
                        position: Int
                    ): Boolean {
                        popupMenu(v) {
                            this.inflate(R.menu.menu_activity)
                            this.setOnMenuItemClickListener { menuItem ->
                                when (menuItem.itemId) {
                                    R.id.joinInActivityItem -> {
                                        viewModel.onJoinInButtonClick(item.model.id)
                                        true
                                    }

                                    else -> {
                                        false
                                    }
                                }
                            }
                        }
                        return true
                    }
                }
            }

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
            Timber.i(activities.toString())
            modelAdapter.updateModels(activities)
        }

        viewModel.actionCompletedEvent.observe(viewLifecycleOwner) {
            toast("Dołączono do aktywności")
        }
    }

    private fun navigateToActivityDetailsFragment(activityId: Long) {
        val direction = ActivitiesFragmentDirections.actionActivitiesFragmentToActivityDetailsFragment(activityId)
        navigateTo(direction)
    }

    private fun navigateToClientActivitiesFragment() {
        val direction = ActivitiesFragmentDirections.actionActivitiesFragmentToClientActivitiesFragment()
        navigateTo(direction)
    }

    private fun navigateToAllActivitiesFragment() {
        val direction = ActivitiesFragmentDirections.actionActivitiesFragmentToAllActivitiesFragment()
        navigateTo(direction)
    }
}