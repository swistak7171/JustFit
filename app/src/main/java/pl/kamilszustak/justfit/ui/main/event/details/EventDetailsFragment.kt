package pl.kamilszustak.justfit.ui.main.event.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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
}