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
    val loadingMutableLiveData = MutableLiveData<Boolean>()
    val throwableMutableLiveData = MutableLiveData<Throwable>()

    /**
     * get data from repository
     */
    fun fetch() {
        viewModelScope.launch {
            loadingMutableLiveData.value = true
            repository.getData()
                .collect {
                    when (it != null) {
                        true -> covidListMutableLiveData.value = it
                        false -> throwableMutableLiveData.value = Throwable(REPOSITORY_ERROR_MESSAGE)
                    }
                    loadingMutableLiveData.value = false
                }
        }
    }

    companion object {
        private const val REPOSITORY_ERROR_MESSAGE = "Bir hata oluştu, lütfen tekrar dene."
    }
}