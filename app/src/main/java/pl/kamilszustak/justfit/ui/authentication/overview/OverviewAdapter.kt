package pl.kamilszustak.justfit.ui.authentication.overview

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.ui.authentication.overview.photo.OverviewPhotoFragment

class OverviewAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private val photosResources: IntArray = intArrayOf(
        R.drawable.overview_1,
        R.drawable.overview_2,
        R.drawable.overview_3,
        R.drawable.overview_4,
        R.drawable.overview_5
    )

    override fun createFragment(position: Int): Fragment {
        val photoResource = photosResources.getOrNull(position)

        return if (photoResource != null) {
            OverviewPhotoFragment.getInstance(photoResource)
        } else {
            throw IllegalArgumentException("Invalid ViewPager position number")
        }
    }

    override fun getItemCount(): Int = photosResources.size
}