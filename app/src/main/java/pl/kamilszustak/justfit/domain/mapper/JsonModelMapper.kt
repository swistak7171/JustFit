package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.DatabaseEntity
import pl.kamilszustak.justfit.domain.model.JsonModel

abstract class JsonModelMapper<J : JsonModel, E : DatabaseEntity> : Mapper<J, E>()