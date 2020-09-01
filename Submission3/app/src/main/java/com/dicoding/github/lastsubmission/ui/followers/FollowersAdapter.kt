package com.dicoding.github.lastsubmission.ui.followers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.data.entity.UserFollowersResponseItem
import com.dicoding.github.lastsubmission.ui.details.UserDetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class FollowersAdapter(val context: Context) :
    RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    private var items = mutableListOf<UserFollowersResponseItem>()


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserFollowersResponseItem) {
            with(itemView) {
                Glide.with(context)
                    .load(data.avatarUrl)
                    .placeholder(R.drawable.ic_user)
                    .circleCrop()
                    .into(img_view_user)

                txt_username.text = data.login
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, UserDetailActivity::class.java).apply {
                        putExtra(UserDetailActivity.USERNAME_KEY, data.login)
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_user, parent, false))
    }

    override fun onBindViewHolder(holder: FollowersAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(data: MutableList<UserFollowersResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }

}
