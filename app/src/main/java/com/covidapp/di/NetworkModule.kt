package com.covidapp.di

import com.covidapp.api.CovidAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author emre.memis@ovidos.com
 */

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val BASE_URL = "https://covid-api-backend.herokuapp.com/"

    /**
     * create a retrofit instance
     */
    @Provides
    @Singleton
    fun retrofitProvides() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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