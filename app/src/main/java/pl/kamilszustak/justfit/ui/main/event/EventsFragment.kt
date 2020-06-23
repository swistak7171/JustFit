package pl.kamilszustak.justfit.ui.main.event

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
import pl.kamilszustak.justfit.databinding.FragmentEventsBinding
import pl.kamilszustak.justfit.domain.item.EventItem
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.navigateTo
import pl.kamilszustak.justfit.util.updateModels
import javax.inject.Inject

class EventsFragment : BaseFragment(R.layout.fragment_events) {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EventsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentEventsBinding
    private val modelAdapter: ModelAdapter<Event, EventItem> by lazy {
        ModelAdapter<Event, EventItem> { event ->
            EventItem(event)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEventsBinding>(
            inflater,
            R.layout.fragment_events,
            container,
            false
        ).apply {
            this.viewModel = this@EventsFragment.viewModel
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
            .apply {
                this.onClickListener = object : ClickListener<EventItem> {
                    override fun invoke(
                        v: View?,
                        adapter: IAdapter<EventItem>,
                        item: EventItem,
                        position: Int
                    ): Boolean {
                        navigateToEventDetailsFragment(item.model.id)
                        return true
                    }
                }
            }

        binding.eventsRecyclerView.apply {
            this.adapter = fastAdapter
        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.eventsResource.data.observe(viewLifecycleOwner) { events ->
            modelAdapter.updateModels(events)
        }
    }

    private fun navigateToEventDetailsFragment(eventId: Long) {
        val direction = EventsFragmentDirections.actionEventsFragmentToEventDetailsFragment(eventId)
        navigateTo(direction)
    }
}