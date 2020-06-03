package pl.kamilszustak.justfit.ui.main.product.client

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
import pl.kamilszustak.justfit.databinding.FragmentClientProductsBinding
import pl.kamilszustak.justfit.domain.item.ClientProductItem
import pl.kamilszustak.justfit.domain.model.product.Product
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.updateModels
import javax.inject.Inject

class ClientProductsFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ClientProductsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentClientProductsBinding
    private val modelAdapter: ModelAdapter<Product, ClientProductItem> by lazy {
        ModelAdapter<Product, ClientProductItem> { product ->
            ClientProductItem(product)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentClientProductsBinding>(
            inflater,
            R.layout.fragment_client_products,
            container,
            false
        ).apply {
            this.viewModel = this@ClientProductsFragment.viewModel
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

        binding.productsRecyclerView.apply {
            this.adapter = fastAdapter
        }
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.productsResource.data.observe(viewLifecycleOwner) { products ->
            modelAdapter.updateModels(products)
        }
    }
}