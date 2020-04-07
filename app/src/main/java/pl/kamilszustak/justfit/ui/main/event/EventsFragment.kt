package pl.kamilszustak.justfit.ui.main.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.binding.view.viewBinding
import pl.kamilszustak.justfit.databinding.FragmentEventsBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class EventsFragment : BaseFragment(R.layout.fragment_events) {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EventsViewModel by viewModels {
        viewModelFactory
    }

    private val binding: FragmentEventsBinding by viewBinding(FragmentEventsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        setListeners()
        observeViewModel()
    }

    private fun initializeRecyclerView() {

    }

    private fun setListeners() {

    }

    private fun observeViewModel() {

    }
}