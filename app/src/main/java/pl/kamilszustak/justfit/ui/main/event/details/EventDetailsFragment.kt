package pl.kamilszustak.justfit.ui.main.event.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import org.jetbrains.anko.support.v4.toast
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentEventDetailsBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class EventDetailsFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EventDetailsViewModel by viewModels {
        viewModelFactory
    }

    private val args: EventDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentEventDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEventDetailsBinding>(
            inflater,
            R.layout.fragment_event_details,
            container,
            false
        ).apply {
            this.viewModel = this@EventDetailsFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
        viewModel.loadData(args.eventId)
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        binding.joinButton.setOnClickListener {
            viewModel.onJoinButtonClick(args.eventId)
        }

        binding.leaveButton.setOnClickListener {
            viewModel.onLeaveButtonClick(args.eventId)
        }
    }

    private fun observeViewModel() {
        viewModel.actionCompletedEvent.observe(viewLifecycleOwner) {
            toast(R.string.action_completed)
        }
    }
}