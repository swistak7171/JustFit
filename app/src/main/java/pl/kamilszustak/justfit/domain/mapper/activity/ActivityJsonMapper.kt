package pl.kamilszustak.justfit.domain.mapper.activity

import pl.kamilszustak.justfit.common.date.DateFormats
import pl.kamilszustak.justfit.domain.mapper.JsonModelMapper
import pl.kamilszustak.justfit.domain.model.activity.ActivityEntity
import pl.kamilszustak.justfit.domain.model.activity.ActivityJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityJsonMapper @Inject constructor() : JsonModelMapper<ActivityJson, ActivityEntity>() {
    override fun map(model: ActivityJson): ActivityEntity {
        val startDateString = "${model.date} ${model.startTime}"
        val endDateString = "${model.date} ${model.endTime}"
        val date = DateFormats.europeanSimpleDateFormat.parse(model.date)
        val startDate = DateFormats.europeanDateFormat.parse(startDateString)
        val endDate = DateFormats.europeanDateFormat.parse(endDateString)

        return ActivityEntity(
            name = model.name,
            leaderName = model.leaderName,
            type = model.type,
            date = date,
            startDate = startDate,
            endDate = endDate,
            isCancelled = model.isCancelled
        ).apply {
            this.id = model.id
        }
    }
}