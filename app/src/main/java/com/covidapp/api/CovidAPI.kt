package com.covidapp.api

import com.covidapp.model.Covid
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author emre.memis@ovidos.com
 */

interface CovidAPI {
    @GET("turkey")
    suspend fun data(
        @Query("access_token")
        accessToken: String
    ): Covid?
}