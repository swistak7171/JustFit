package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.date.DateFormats
import pl.kamilszustak.justfit.databinding.ItemActivitiesListBinding
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.util.setFormattedText

class ActivityItem(activity: Activity) : ModelAbstractBindingItem<Activity, ItemActivitiesListBinding>(activity) {
    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_activity_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemActivitiesListBinding =
        ItemActivitiesListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemActivitiesListBinding, payloads: List<Any>) {
        with(binding) {
            nameTextView.text = model.name
            typeTextView.text = model.type
            leaderNameTextView.text = model.leaderName

            val date = DateFormats.simpleDateFormat.format(model.startDate)
            val startTime = DateFormats.simpleHourFormat.format(model.startDate)
            val endTime = DateFormats.simpleHourFormat.format(model.endDate)

            dateTextView.text = date
            timeTextView.setFormattedText(R.string.start_time_and_end_time, startTime, endTime)
        }
    }

    override fun unbindView(binding: ItemActivitiesListBinding) {
        with(binding) {
            nameTextView.text = null
            typeTextView.text = null
            leaderNameTextView.text = null
            dateTextView.text = null
            timeTextView.text = null
        }
    }
}