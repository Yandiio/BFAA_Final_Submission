package com.dicoding.github.lastsubmission.ui.favorite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import kotlinx.android.synthetic.main.item_row_fav_user.view.*

class FavoriteAdapter(val context: Context): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserFavorite) {
            with(itemView) {
                Glide.with(context)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(img_fav_user)

                txt_fav_username.text = user.username
                txt_fav_bio.text = user.bio ?: context.getString(R.string.empty)
                txt_fav_location.text = user.location ?: context.getString(R.string.empty)
                txt_title_fav_following.text = user.following.toString()
                txt_title_fav_followers.text  = user.followers.toString()
                txt_title_fav_repository.text = user.publicRepos.toString()
                txt_fav_company.text = user.company ?: context.getString(R.string.no_company)

                itemView.setOnClickListener {
                   Intent(itemView.context, UserDetailActivity::class.java).apply {
                        putExtra(UserDetailActivity.USERNAME_KEY, user.username)
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row_fav_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
    }


    override fun getItemCount(): Int = items.size
}