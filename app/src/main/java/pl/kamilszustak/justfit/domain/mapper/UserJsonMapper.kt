package pl.kamilszustak.justfit.domain.mapper

import pl.kamilszustak.justfit.domain.model.user.User
import pl.kamilszustak.justfit.domain.model.user.UserJson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserJsonMapper @Inject constructor() : JsonModelMapper<UserJson, User>() {
    override fun map(model: UserJson): User = User(
        email = model.email,
        username = model.username,
        name = model.name,
        surname = model.surname,
        phoneNumber = model.phoneNumber
    ).apply {
        this.id = model.id
    }
}