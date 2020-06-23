package pl.kamilszustak.justfit.domain.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import pl.kamilszustak.justfit.R
import pl.kamilszustak.justfit.common.date.DateFormats
import pl.kamilszustak.justfit.databinding.ItemEventsListBinding
import pl.kamilszustak.justfit.domain.model.event.Event
import pl.kamilszustak.justfit.util.setFormattedText

class EventItem(event: Event) : ModelAbstractBindingItem<Event, ItemEventsListBinding>(event) {
    override var identifier: Long
        get() = this.model.id
        set(value) {}

    override val type: Int = R.id.fastadapter_event_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEventsListBinding =
        ItemEventsListBinding.inflate(inflater, parent, false)

    override fun bindView(binding: ItemEventsListBinding, payloads: List<Any>) {
        with(binding) {
            nameTextView.text = model.name

            val startDateText = DateFormats.dateFormat.format(model.startDate)
            startDateTextView.setFormattedText(R.string.event_start_date, startDateText)

            val endDateText = DateFormats.dateFormat.format(model.endDate)
            endDateTextView.setFormattedText(R.string.event_end_date, endDateText)
        }
    }

    override fun unbindView(binding: ItemEventsListBinding) {
        with(binding) {
            nameTextView.text = null
            startDateTextView.text = null
            endDateTextView.text = null
        }
    }
}