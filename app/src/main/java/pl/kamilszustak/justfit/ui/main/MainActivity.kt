package pl.kamilszustak.justfit.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.binding.view.viewBinding
import pl.kamilszustak.justfit.databinding.ActivityMainBinding
import pl.kamilszustak.justfit.ui.base.BaseActivity
import pl.kamilszustak.justfit.util.setupWithNavController

class MainActivity : BaseActivity(R.layout.activity_main) {
    private var currentNavController: LiveData<NavController>? = null
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState)
        }

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_profile,
            R.navigation.navigation_event,
            R.navigation.navigation_equipment,
            R.navigation.navigation_product,
            R.navigation.navigation_employee
        )

        val controller = binding.mainBottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.mainNavHostFragment,
            intent = intent
        )

        controller.observe(this) {
            setupActionBarWithNavController(it)
        }

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}