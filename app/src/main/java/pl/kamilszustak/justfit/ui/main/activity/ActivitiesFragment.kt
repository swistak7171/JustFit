package pl.kamilszustak.justfit.ui.main.activity

import android.app.Activity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.justfit.databinding.FragmentActivitiesBinding
import pl.kamilszustak.justfit.domain.item.EmployeeItem
import pl.kamilszustak.justfit.domain.model.employee.Employee
import pl.kamilszustak.justfit.ui.base.BaseFragment
import javax.inject.Inject

class ActivitiesFragment : BaseFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: ActivitiesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentActivitiesBinding
    private val modelAdapter: ModelAdapter<Activity, ActivityItem> by lazy {
        ModelAdapter<Activity, ActivityItem> { activity ->
            ActivityItem(activity)
        }
    }
}