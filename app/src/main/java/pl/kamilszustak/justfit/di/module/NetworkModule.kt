package pl.kamilszustak.justfit.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.kamilszustak.justfit.common.moshi.adapter.LocalDateTimeFieldAdapter
import pl.kamilszustak.justfit.di.api.ClientApi
import pl.kamilszustak.justfit.di.api.EmployeeApi
import pl.kamilszustak.justfit.di.api.EquipmentApi
import pl.kamilszustak.justfit.di.api.EventApi
import pl.kamilszustak.justfit.di.api.ProductApi
import pl.kamilszustak.justfit.network.CLIENT_API_BASE_URL
import pl.kamilszustak.justfit.network.EMPLOYEE_API_BASE_URL
import pl.kamilszustak.justfit.network.EQUIPMENT_API_BASE_URL
import pl.kamilszustak.justfit.network.EVENT_API_BASE_URL
import pl.kamilszustak.justfit.network.PRODUCT_API_BASE_URL
import pl.kamilszustak.justfit.network.interceptor.AuthorizationInterceptor
import pl.kamilszustak.justfit.network.service.ClientApiService
import pl.kamilszustak.justfit.network.service.EmployeeApiService
import pl.kamilszustak.justfit.network.service.EquipmentApiService
import pl.kamilszustak.justfit.network.service.EventApiService
import pl.kamilszustak.justfit.network.service.ProductApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(LocalDateTimeFieldAdapter())
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
            .withNullSerialization()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authorizationInterceptor: AuthorizationInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @ClientApi
    fun provideClientApiRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CLIENT_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @EquipmentApi
    fun provideEquipmentApiRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EQUIPMENT_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @EmployeeApi
    fun provideEmployeeApiRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EMPLOYEE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @EventApi
    fun provideEventApiRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(EVENT_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    @ProductApi
    fun provideProductApiRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PRODUCT_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideClientApiService(@ClientApi retrofit: Retrofit): ClientApiService =
        retrofit.create()

    @Provides
    @Singleton
    fun provideEquipmentApiService(@EquipmentApi retrofit: Retrofit): EquipmentApiService =
        retrofit.create()

    @Provides
    @Singleton
    fun provideEmployeeApiService(@EmployeeApi retrofit: Retrofit): EmployeeApiService =
        retrofit.create()

    @Provides
    @Singleton
    fun provideEventApiService(@EventApi retrofit: Retrofit): EventApiService =
        retrofit.create()

    @Provides
    @Singleton
    fun provideProductApiService(@ProductApi retrofit: Retrofit): ProductApiService =
        retrofit.create()
}