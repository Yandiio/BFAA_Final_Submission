package com.dicoding.github.contentprovider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_main.view.*

class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userFavorite: UserFavorite) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userFavorite.avatar_url)
                    .circleCrop()
                    .into(img_fav_user)

                txt_fav_username.text = userFavorite.username
                txt_title_fav_followers.text = userFavorite.followers
                txt_title_fav_following.text = userFavorite.following
                txt_title_fav_repository.text = userFavorite.public_repos ?: mContext.getString(R.string.empty)
                txt_fav_location.text = userFavorite.location ?: mContext.getString(R.string.empty)
                txt_fav_company.text = userFavorite.company ?: mContext.getString(R.string.no_company)
            }
        }
    }

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_view_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}