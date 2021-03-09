package com.covidapp.api

import com.covidapp.model.Covid
import retrofit2.http.GET

/**
 * @author emre.memis@ovidos.com
 */

interface CovidAPI {
    @GET("v2/key-value-stores/28ljlt47S5XEd1qIi/records/LATEST?disableRedirect=true")
    suspend fun getCovidData(): Covid?
}