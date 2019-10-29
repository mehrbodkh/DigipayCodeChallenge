package com.mehrbod.digipaycodechallenge.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mehrbod.digipaycodechallenge.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupViews()
    }

    private fun setupViews() {
        setupSearchButton()
        bindSearchResultList()
    }

    private fun setupSearchButton() {
        search_button.setOnClickListener {
            viewModel.doHandleSearch(search_edit_text.text.toString())
        }
    }

    private fun bindSearchResultList() {
        viewModel.searchResult.observe(this,
            Observer { results ->

            })
    }
}
