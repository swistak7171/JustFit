package pl.kamilszustak.justfit.domain.mapper.activity

import pl.kamilszustak.justfit.domain.mapper.Mapper
import pl.kamilszustak.justfit.domain.model.activity.Activity
import pl.kamilszustak.justfit.domain.model.activity.ActivityWithEquipment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityWithEquipmentMapper @Inject constructor() : Mapper<ActivityWithEquipment, Activity>() {
    override fun map(model: ActivityWithEquipment): Activity =
        Activity(
            id = model.activity.id,
            name = model.activity.name,
            leaderName = model.activity.leaderName,
            type = model.activity.type,
            date = model.activity.date,
            startDate = model.activity.startDate,
            endDate = model.activity.endDate,
            usedEquipment = model.usedEquipment,
            isCancelled = model.activity.isCancelled
        )
}