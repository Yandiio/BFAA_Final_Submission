package com.dicoding.github.lastsubmission.ui.favorite

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(private val viewGroup: View) : RecyclerView.ViewHolder(viewGroup) {
        fun bind(user: UserFavorite) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}