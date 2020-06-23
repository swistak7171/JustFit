package pl.kamilszustak.justfit.network.interceptor

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import pl.kamilszustak.justfit.data.repository.UserDetailsRepository
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val email = userDetailsRepository.getValue<String>(UserDetailsRepository.UserDetailsKey.USER_EMAIL)
        val password = userDetailsRepository.getValue<String>(UserDetailsRepository.UserDetailsKey.USER_PASSWORD)
        val credentials = Credentials.basic(email, password)

        val request = originalRequest.newBuilder()
            .header("Authorization", credentials)
            .header("Content-Type", "application/json")
            .method(originalRequest.method, originalRequest.body)
            .build()

        return chain.proceed(request)
    }
}