package pl.kamilszustak.justfit.ui.main.activity.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentClientActivitiesBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class ClientActivitiesFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ClientActivitiesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentClientActivitiesBinding
    // private val modelAdapter: ModelAdapter<Product, ClientProductItem> by lazy {
    //     ModelAdapter<Product, ClientProductItem> { product ->
    //         ClientProductItem(product)
    //     }
    // }

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

    }

    private fun setListeners() {

    }

    private fun observeViewModel() {

    }
}