package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.entity.DatabaseEntity
import pl.kamilszustak.justfit.domain.model.json.JsonModel

abstract class JsonModelMapper<J : JsonModel, E : DatabaseEntity> : Mapper<J, E>()