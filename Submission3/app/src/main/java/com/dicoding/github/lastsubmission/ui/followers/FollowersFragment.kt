package com.dicoding.github.lastsubmission.ui.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.core.base.BaseFragment
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.util.setGONE
import com.dicoding.github.lastsubmission.core.util.setVisible
import com.dicoding.github.lastsubmission.data.entity.UserFollowersResponseItem
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import kotlinx.android.synthetic.main.followers_fragment.*
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class FollowersFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FollowersViewModel

    private var lists = mutableListOf<UserFollowersResponseItem>()

    private val followersAdapter: FollowersAdapter by lazy {
        FollowersAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.followers_fragment, container, false)
    }


    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        handleUser()
        initObserver()
        initRecycler()
    }

    private fun handleUser() {
        val activity = activity as UserDetailActivity
        val username: String? = activity.getUsername()
        viewModel.getUserFollowers(username!!)
    }

    private fun initRecycler() {
        recycler_view.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = followersAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[FollowersViewModel::class.java]
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            handleState(it)
        })

        viewModel.resultUserFollowers.observe(viewLifecycleOwner, Observer {
            handleUserFollower(it)
        })
    }

    private fun handleUserFollower(data: List<UserFollowersResponseItem>) {
        handleEmptyFollower(data)
        lists.clear()
        lists.addAll(data)
        followersAdapter.setItems(data = lists)
    }

    private fun handleEmptyFollower(data: List<UserFollowersResponseItem>) {
        if (data.isEmpty()) {
            empty_base.setVisible()
            recycler_view.setGONE()
        } else {
            empty_base.setGONE()
            recycler_view.setVisible()
        }
    }

    private fun handleState(loader: LoaderState) {
        if (loader is LoaderState.showLoading) {
            baseLoading.setVisible()
            recycler_view.setGONE()
        } else {
            baseLoading.setGONE()
            recycler_view.setVisible()
        }
    }

}