package com.dicoding.github.lastsubmission.core.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(imgSrc : Any) {
    Glide.with(context)
        .load(imgSrc)
        .apply(RequestOptions().transform(RoundedCorners(15)))
        .into(this)
}