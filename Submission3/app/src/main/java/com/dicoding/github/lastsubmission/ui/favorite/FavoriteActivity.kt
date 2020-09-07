package com.dicoding.github.lastsubmission.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.core.base.BaseActivity
import com.dicoding.github.lastsubmission.core.util.setGONE
import com.dicoding.github.lastsubmission.core.util.setVisible
import com.dicoding.github.lastsubmission.data.db.entity.UserFavorite
import com.dicoding.github.lastsubmission.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import javax.inject.Inject

class FavoriteActivity : BaseActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var viewModel: FavoriteViewModel

    private val listUser = mutableListOf<UserFavorite>()

    private val userFavoriteAdapter : FavoriteAdapter by lazy {
        FavoriteAdapter(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        initViewModels()
        initObserver()
        initRecyclerView()
        initToolbar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_alarm) {
            Intent(this, SettingsActivity::class.java ).also {
                startActivity(it)
            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_view.adapter = userFavoriteAdapter
    }

    private fun initObserver() {
        viewModel.resultUserDb.observe(this, Observer {
            handleDbUserFav(it)
        })
    }

    private fun handleDbUserFav(list: List<UserFavorite>) {
        handleWhenDataEmpty(list)
        listUser.clear()
        listUser.addAll(list)
        userFavoriteAdapter.setItems(listUser)
        userFavoriteAdapter.notifyDataSetChanged()
    }

    private fun handleWhenDataEmpty(user: List<UserFavorite>) {
        if (user.isEmpty()) {
            recycler_view.setGONE()
            base_empty_fav.setVisible()
        } else {
            recycler_view.setVisible()
            base_empty_fav.setGONE()
        }
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelProvider)[FavoriteViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_alarm, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.fetchAllUserFavorite()
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = getString(R.string.favorite)
    }
}