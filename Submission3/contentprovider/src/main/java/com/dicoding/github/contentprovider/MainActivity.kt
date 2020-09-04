package com.dicoding.github.contentprovider

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDataFromContentProvider()
    }

    private val mainAdapter : MainAdapter by lazy {
        MainAdapter(applicationContext)
    }

    private fun getDataFromContentProvider() {
        val table = "user_favorite_table"
        val authority = "com.dicoding.github.lastsubmission.provider"

        val uri = Uri.Builder()
            .scheme("content")
            .authority(authority)
            .appendPath(table)
            .build()

        val contentResolver = this.contentResolver
        val cursor = contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )

        if (cursor != null) {
            initAdapter(cursor)
        } else {
            initAdapter(cursor)
        }
    }

    private fun initAdapter(cursor: Cursor?) {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        cursor?.let{
            mainAdapter.setItems(it.toListUserFav())
        }
    }
}
