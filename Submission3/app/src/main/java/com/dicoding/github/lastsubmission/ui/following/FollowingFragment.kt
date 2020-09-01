package com.dicoding.github.lastsubmission.ui.following

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.core.base.BaseFragment
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.state.ResultState
import com.dicoding.github.lastsubmission.core.util.setGONE
import com.dicoding.github.lastsubmission.core.util.setVisible
import com.dicoding.github.lastsubmission.data.entity.UserFollowingResponseItem
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.following_fragment.*
import kotlinx.android.synthetic.main.following_fragment.baseLoading
import kotlinx.android.synthetic.main.following_fragment.recycler_view
import javax.inject.Inject

class FollowingFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FollowingViewModel

    private var lists = mutableListOf<UserFollowingResponseItem>()

    private val followingAdapter: FollowingAdapter by lazy {
        FollowingAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.following_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        getUsernameDetail()
        initObserver()
        initRecyclerView()
    }

    private fun getUsernameDetail() {
        val activity = activity as UserDetailActivity
        val username: String? = activity.getUsername()

        viewModel.getResultFollowing(username!!)
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = followingAdapter
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FollowingViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleFollowingState(it)
        })

        viewModel.resultUserFollowing.observe(viewLifecycleOwner, Observer {
            handleDataUserFollowing(it)
        })
    }

    private fun handleFollowingState(loading: LoaderState) {
        if (loading is LoaderState.showLoading) {
            baseLoading.setVisible()
            recycler_view.setGONE()
        } else {
            baseLoading.setGONE()
            recycler_view.setVisible()
        }
    }

    private fun handleDataUserFollowing(data: List<UserFollowingResponseItem>) {
        handleDataEmpty(data)
        lists.clear()
        lists.addAll(data)
        followingAdapter.setData(lists)
    }

    private fun handleDataEmpty(data: List<UserFollowingResponseItem>) {
        if (data.isEmpty()) {
            empty_base.setVisible()
            recycler_view.setGONE()
        } else {
            empty_base.setGONE()
            recycler_view.setVisible()
        }
    }

}