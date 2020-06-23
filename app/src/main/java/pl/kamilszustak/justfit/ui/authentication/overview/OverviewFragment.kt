package pl.kamilszustak.justfit.ui.authentication.overview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.binding.view.viewBinding
import pl.kamilszustak.justfit.databinding.FragmentOverviewBinding
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class OverviewFragment : BaseFragment(R.layout.fragment_overview) {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: OverviewViewModel by viewModels {
        viewModelFactory
    }

    private val binding: FragmentOverviewBinding by viewBinding(FragmentOverviewBinding::bind)
    private val viewPagerAdapter: OverviewAdapter by lazy {
        OverviewAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewPager()
    }

    private fun initializeViewPager() {
        binding.photosViewPager.apply {
            this.adapter = viewPagerAdapter
            this.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.pageIndicatorView.setSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }
            })
        }
    }
}