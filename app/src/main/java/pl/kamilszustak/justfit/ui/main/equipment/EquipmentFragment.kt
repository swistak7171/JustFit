package pl.kamilszustak.justfit.ui.main.equipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.LongClickListener
import com.mikepenz.fastadapter.adapters.ModelAdapter
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import org.jetbrains.anko.design.snackbar
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.databinding.FragmentEquipmentBinding
import pl.kamilszustak.justfit.domain.item.EquipmentItem
import pl.kamilszustak.justfit.domain.model.equipment.Equipment
import pl.kamilszustak.justfit.ui.base.BaseFragment
import pl.kamilszustak.justfit.util.popupMenu
import pl.kamilszustak.justfit.util.updateModels
import javax.inject.Inject

class EquipmentFragment : BaseFragment(R.layout.fragment_equipment) {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private val viewModel: EquipmentViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentEquipmentBinding

    private val modelAdapter: ModelAdapter<Equipment, EquipmentItem> by lazy {
        ModelAdapter<Equipment, EquipmentItem>() { equipment ->
            EquipmentItem(equipment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentEquipmentBinding>(
            inflater,
            R.layout.fragment_equipment,
            container,
            false
        ).apply {
            this.viewModel = this@EquipmentFragment.viewModel
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
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            this.onLongClickListener = object : LongClickListener<EquipmentItem> {
                override fun invoke(
                    v: View,
                    adapter: IAdapter<EquipmentItem>,
                    item: EquipmentItem,
                    position: Int
                ): Boolean {
                    popupMenu(v) {
                        inflate(R.menu.menu_equipment_list_item)
                        menu.findItem(R.id.setAsUnavailableItem)?.isEnabled = item.model.isAvailable
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.setAsUnavailableItem -> {
                                    viewModel.onSetAsUnavailableButtonClick(item.model.id)
                                }
                            }

                            true
                        }
                    }

                    return true
                }
            }
        }

        binding.equipmentRecyclerView.apply {
            this.adapter = fastAdapter
            this.itemAnimator = SlideInRightAnimator()
        }
    }

    private fun setListeners() {
        binding.equipmentFiltersGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            viewModel.onCheckedButtonChange(binding.equipmentFiltersGroup.checkedButtonId)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    private fun observeViewModel() {
        viewModel.equipmentResource.data.observe(viewLifecycleOwner) { equipment ->
            modelAdapter.updateModels(equipment)
        }

        viewModel.error.observe(viewLifecycleOwner) { messageResource ->
            val message = getString(messageResource)
            view?.snackbar(message)
        }
    }
}