package com.mehrbod.digipaycodechallenge.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mehrbod.digipaycodechallenge.R
import com.mehrbod.digipaycodechallenge.api.SearchResult
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val viewModel by viewModels<SearchViewModel>()

    private val searchAdapter = SearchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupViews()
    }

    private fun setupViews() {
        setupSearchButton()
        setupList()
        bindResults()
    }

    private fun setupSearchButton() {
        search_button.setOnClickListener {
            viewModel.doHandleSearch(search_edit_text.text.toString())
        }
    }

    private fun setupList() {
        search_list.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun bindResults() {
        viewModel.searchResult.observe(this,
            Observer { latestReleases ->
                latestReleases?.let { updateList(it) }
            })
    }

    private fun updateList(searchResult: SearchResult) {
        searchAdapter.songs = searchResult
        searchAdapter.notifyDataSetChanged()
    }
}
