package com.covidapp.di

import com.covidapp.api.CovidAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author emre.memis@ovidos.com
 */

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val BASE_URL = "https://covid-api-backend.herokuapp.com/"
    private const val CALL_TIMEOUT = 45 * 1000L
    private const val CONNECT_TIMEOUT = 25 * 1000L
    private const val READ_TIMEOUT = 45 * 1000L

    /**
     * create a http client for retrofit
     */
    @Provides
    @Singleton
    fun httpClientProvides() : OkHttpClient {
        return OkHttpClient.Builder()
                .callTimeout(CALL_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build()
    }

    /**
     * create a retrofit instance
     * @param httpClient from dependency injection
     * @see httpClientProvides
     */
    @Provides
    @Singleton
    fun retrofitProvides(httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
    }

    /**
     * create a CovidApi instance
     * @param retrofit from dependency injection
     * @see retrofitProvides
     */
    @Provides
    @Singleton
    fun covidApiProvides(retrofit: Retrofit): CovidAPI {
        return retrofit.create(CovidAPI::class.java)
    }
}