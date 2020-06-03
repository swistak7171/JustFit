package pl.kamilszustak.justfit.ui.main.product

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
import pl.kamilszustak.justfit.databinding.FragmentProductsBinding
import pl.kamilszustak.justfit.domain.item.ProductItem
import pl.kamilszustak.justfit.domain.model.product.Product
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.updateModels
import javax.inject.Inject

class ProductsFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ProductsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentProductsBinding
    private val modelAdapter: ModelAdapter<Product, ProductItem> by lazy {
        ModelAdapter<Product, ProductItem> { product ->
            ProductItem(product)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProductsBinding>(
            inflater,
            R.layout.fragment_products,
            container,
            false
        ).apply {
            this.viewModel = this@ProductsFragment.viewModel
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