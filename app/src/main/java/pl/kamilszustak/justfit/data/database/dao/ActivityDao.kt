package pl.kamilszustak.justfit.data.database.dao

import androidx.room.Dao
import pl.kamilszustak.justfit.domain.model.activity.ActivityEntity

@Dao
interface ActivityDao : BaseDao<ActivityEntity> {
}