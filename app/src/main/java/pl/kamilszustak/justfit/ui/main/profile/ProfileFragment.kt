package pl.kamilszustak.justfit.ui.main.profile

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
import org.jetbrains.anko.support.v4.startActivity
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentProfileBinding
import pl.kamilszustak.justfit.ui.authentication.AuthenticationActivity
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.navigateTo
import javax.inject.Inject

class ProfileFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ProfileViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentProfileBinding>(
            inflater,
            R.layout.fragment_profile,
            container,
            false
        ).apply {
            this.viewModel = this@ProfileFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logoutItem -> {
                viewModel.onLogoutButtonClick()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        binding.clientProductsButton.setOnClickListener {
            navigateToClientProductsFragment()
        }
    }

    private fun observeViewModel() {
        viewModel.logoutCompleted.observe(viewLifecycleOwner) {
            startActivity<AuthenticationActivity>()
            requireActivity().finish()
        }
    }

    private fun navigateToClientProductsFragment() {
        val direction = ProfileFragmentDirections.actionProfileFragmentToClientProductsFragment()
        navigateTo(direction)
    }
}