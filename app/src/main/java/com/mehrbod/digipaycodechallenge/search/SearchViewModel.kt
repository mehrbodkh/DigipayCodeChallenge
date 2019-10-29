package com.mehrbod.digipaycodechallenge.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class SearchViewModel : ViewModel(), KoinComponent {
    private val repository: SearchRepository by inject()

    val searchResult = MutableLiveData<List<String>>()

    fun doHandleSearch(query: String) {
        viewModelScope.launch {
            val result = repository.doHandleSearch(query)
            searchResult.value = result
        }
    }
}