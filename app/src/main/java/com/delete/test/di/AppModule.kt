package com.delete.test.di

import android.content.Context
import androidx.room.Room
import com.delete.test.BuildConfig
import com.delete.test.data.db.CurrencyDAO
import com.delete.test.data.db.CurrencyDB
import com.delete.test.data.network.CurrencyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    private val timeout: Long = 60L

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addNetworkInterceptor(Interceptor { chain ->
                /* val originalRequest = chain.request()
                 val originalUrl = originalRequest.url
                 val newUrl = originalUrl.newBuilder()
                     .addQueryParameter("apikey", BuildConfig.authToken)
                     .build()
                 val newRequest = originalRequest.newBuilder().url(newUrl).build()
                 return@Interceptor chain.proceed(newRequest)*/
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("apikey", BuildConfig.authToken)
                chain.proceed(requestBuilder.build())
            }).build()

    @Singleton
    @Provides
    fun provideAPI(client: OkHttpClient): CurrencyAPI =
        Retrofit.Builder()
            .baseUrl(BuildConfig.server_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyAPI::class.java)

    @Singleton
    @Provides
    fun provideDAO(@ApplicationContext context: Context): CurrencyDAO =
        Room.databaseBuilder(
            context,
            CurrencyDB::class.java,
            "currency"
        )
            .fallbackToDestructiveMigration()
            .build().currencyDao()
}