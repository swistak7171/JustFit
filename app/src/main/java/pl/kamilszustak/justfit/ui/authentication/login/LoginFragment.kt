package pl.kamilszustak.justfit.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentLoginBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.ui.main.MainActivity
import javax.inject.Inject

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        ).apply {
            this.viewModel = this@LoginFragment.viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.onLoginButtonClick()
        }
    }

    private fun observeViewModel() {
        viewModel.completed.observe(viewLifecycleOwner) {
            startActivity<MainActivity>()
            requireActivity().finish()
        }

        viewModel.error.observe(viewLifecycleOwner) { messageResource ->
            val message = getString(messageResource)
            view?.snackbar(message)
        }
    }
}