package com.mehrbod.digipaycodechallenge.search

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository {
    suspend fun doHandleSearch(query: String) = withContext(Dispatchers.IO) {

        listOf("$query _0", "$query _1")
    }
}