package pl.kamilszustak.justfit.ui.authentication.overview.photo

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.os.bundleOf
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.binding.view.viewBinding
import pl.kamilszustak.justfit.databinding.FragmentOverviewPhotoBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment

class OverviewPhotoFragment : BaseFragment(R.layout.fragment_overview_photo) {
    private val binding: FragmentOverviewPhotoBinding by viewBinding(FragmentOverviewPhotoBinding::bind)
    private val args: OverviewPhotoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPhoto()
    }

    private fun loadPhoto() {
        Glide.with(this)
            .load(args.photoResource)
            .into(binding.photoImageView)
    }

    companion object {
        fun getInstance(@DrawableRes photoResource: Int): OverviewPhotoFragment {
            val bundle = bundleOf("photoResource" to photoResource)

            return OverviewPhotoFragment().apply {
                this.arguments = bundle
            }
        }
    }
}