package com.dicoding.github.lastsubmission.ui.main

import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.core.BaseActivity
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.util.setGONE
import com.dicoding.github.lastsubmission.core.util.setVisible
import com.dicoding.github.lastsubmission.data.entity.UserSearchResponseItem
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: MainViewModel

    private val items = mutableListOf<UserSearchResponseItem>()

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchUsers()
        initViewModel()
        initRecycleView()
        initObserver()
    }

    private fun initRecycleView() {
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = mainAdapter
        mainAdapter.setActivity(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            it?.let {
                handleStateLoading(it)
            }
        })
        viewModel.resultFromApi.observe(this, Observer {
            it?.let {
                handleUserFromAPI(it)
            }
        })
        viewModel.networkError.observe(this, Observer {
            it?.let {

            }
        })
    }

    private fun searchUsers() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isNotEmpty()) {
                    items.clear()
                    viewModel.getUserFromApi(query)
                    search_view.clearFocus()
                    setIllustration(false)
                }

                if (query.isEmpty()) {
                    search_view.clearFocus()
                    setIllustration(true)
                }

                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                return false
            }

        })


        search_view.setOnCloseListener(object : SearchView.OnCloseListener,
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                items.clear()
                mainAdapter.clearItems()
                setIllustration(true)
                return true
            }

        })
    }

    private fun handleUserFromAPI(result: List<UserSearchResponseItem>) {
        items.clear()
        items.addAll(result)
        mainAdapter.setItems(items)
    }


    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.showLoading) {
            baseLoading.setVisible()
            setIllustration(false)
            recycler_view.setGONE()
        } else {
            baseLoading.setGONE()
            setIllustration(false)
            recycler_view.setVisible()
        }
    }

    private fun setIllustration(status: Boolean) {
        if (status) {
            base_empty.setVisible()
        } else {
            base_empty.setGONE()
        }
    }
}