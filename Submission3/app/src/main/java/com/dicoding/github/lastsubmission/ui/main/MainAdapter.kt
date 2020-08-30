package com.dicoding.github.lastsubmission.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.data.entity.UserSearchResponseItem
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var items = mutableListOf<UserSearchResponseItem>()
    lateinit var mainActivity: MainActivity

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserSearchResponseItem, activity: MainActivity) {
            with(itemView) {
                Glide.with(mContext)
                    .load(data.avatarUrl!!)
                    .apply(com.bumptech.glide.request.RequestOptions().circleCrop())
                    .placeholder(com.dicoding.github.lastsubmission.R.drawable.ic_user)
                    .into(img_view_user)

                txt_username.text = data.login
            }

            itemView.setOnClickListener {
                val option : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, itemView.img_view_user, "image"
                )

                val intent = Intent(itemView.context, UserDetailActivity::class.java).apply {
                    putExtra(UserDetailActivity.USERNAME_KEY, data.login)
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.also {
                    itemView.context.startActivity(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        return MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_row_user, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position], activity = mainActivity)
    }

    override fun getItemCount(): Int = items.size

    fun setActivity(activity: MainActivity) {
        this.mainActivity = activity
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun setItems(data: MutableList<UserSearchResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }
}