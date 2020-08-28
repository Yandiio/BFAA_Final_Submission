package com.dicoding.github.lastsubmission.ui.main

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.github.lastsubmission.data.entity.UserSearchResponseItem

class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val items = mutableListOf<UserSearchResponseItem>()
    lateinit var mainActivity: MainActivity

    inner class MainViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserSearchResponseItem, activity: MainActivity) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = items.size
}