package pl.kamilszustak.justfit.ui.main.profile.contact

import android.os.Bundle
import android.view.View
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.binding.view.viewBinding
import pl.kamilszustak.justfit.databinding.FragmentContactBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment

class ContactFragment : BaseFragment(R.layout.fragment_contact) {
    private val binding: FragmentContactBinding by viewBinding(FragmentContactBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {

    }
}