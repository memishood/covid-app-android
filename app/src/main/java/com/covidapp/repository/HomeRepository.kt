package com.covidapp.repository

import com.covidapp.api.CovidAPI
import com.covidapp.model.Covid
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @author emre.memis@ovidos.com
 */

@ViewModelScoped
class HomeRepository @Inject constructor(private val api: CovidAPI) {
    /**
     * get covid data with coroutines
     */
    suspend fun getData() : Flow<Covid?> {
        return flow {
            val data = api.data(accessToken = ACCESS_TOKEN)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val ACCESS_TOKEN = "44GBbI9VC3xXl4OVT1O3"
    }
}