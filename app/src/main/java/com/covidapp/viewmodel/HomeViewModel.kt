package com.covidapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.covidapp.model.Covid
import com.covidapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author emre.memis@ovidos.com
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {
    val covidListMutableLiveData = MutableLiveData<Covid>()

    /**
     * get data from repository
     */
    init {
        viewModelScope.launch {
            repository.getData()
                .collect {
                    it?.let { data -> covidListMutableLiveData.value = data }
                }
        }
    }
}