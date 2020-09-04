package com.dicoding.github.lastsubmission.ui.following

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.data.entity.UserFollowingResponseItem
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity.Companion.USERNAME_KEY
import kotlinx.android.synthetic.main.item_row_user.view.*

class FollowingAdapter(val context: Context) : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {

    private var items = mutableListOf<UserFollowingResponseItem>()

    inner class FollowingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserFollowingResponseItem) {
            with(itemView) {
                Glide.with(context)
                    .load(data.avatarUrl)
                    .placeholder(R.drawable.ic_user)
                    .circleCrop()
                    .into(img_view_user)

                txt_username.text = data.login

                itemView.setOnClickListener {
                   Intent(itemView.context, UserDetailActivity::class.java).apply {
                        putExtra(USERNAME_KEY, data.login)
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_user, parent, false))
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(lists: MutableList<UserFollowingResponseItem>) {
        this.items = lists
        notifyDataSetChanged()
    }
}