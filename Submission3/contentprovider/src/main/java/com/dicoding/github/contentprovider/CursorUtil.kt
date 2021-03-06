package com.dicoding.github.contentprovider

import android.database.Cursor

fun Cursor.toListUserFav() : ArrayList<UserFavorite> {
    val userFavoriteList = ArrayList<UserFavorite>()

    apply {
        while (moveToNext()) {
            userFavoriteList.add(
                toUserFavoriteEntity()
            )
        }
    }

    return userFavoriteList
}

fun Cursor.toUserFavoriteEntity() : UserFavorite =
    UserFavorite(
        getString(getColumnIndexOrThrow("username")),
        getString(getColumnIndexOrThrow("avatar_url")),
        getString(getColumnIndexOrThrow("company")),
        getString(getColumnIndexOrThrow("public_repos")),
        getString(getColumnIndexOrThrow("followers")),
        getString(getColumnIndexOrThrow("following")),
        getString(getColumnIndexOrThrow("location"))
    )