package pl.kamilszustak.justfit.domain.usecase.user

interface IsUserAuthenticated {
    operator fun invoke(): Boolean
}