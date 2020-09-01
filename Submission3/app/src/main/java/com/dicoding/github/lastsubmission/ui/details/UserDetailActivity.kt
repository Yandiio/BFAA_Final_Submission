package com.dicoding.github.lastsubmission.ui.details

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.github.lastsubmission.R
import com.dicoding.github.lastsubmission.core.base.BaseActivity
import com.dicoding.github.lastsubmission.core.state.LoaderState
import com.dicoding.github.lastsubmission.core.util.setGONE
import com.dicoding.github.lastsubmission.core.util.setVisible
import com.dicoding.github.lastsubmission.data.db.UserDetails
import com.dicoding.github.lastsubmission.ui.favorite.FavoriteActivity
import com.dicoding.github.lastsubmission.ui.settings.SettingsActivity
import com.dicoding.github.lastsubmission.ui.viewpager.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: UserDetailViewModel

    private var userDetail: UserDetails? = null

    private var menu: Menu? = null

    private var username: String? = null


    companion object {
        const val USERNAME_KEY = "username_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        handlekeyIntent()
        initViewModels()
        initObserver()
        getDataFromResource()
        initToolbar()
        initPageAdapter()
    }

    private fun getDataFromResource() {
        username?.let {
            viewModel.getUserDetail(it)
        }
    }


    fun getUsername(): String? {
        return username
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = 0f
        supportActionBar?.title = username + " \'s Profile"
    }

    private fun initPageAdapter()  {
        val sectionPageAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionPageAdapter
        tabs.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_alarm) {
            val intent = Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }

        if (item.itemId == R.id.menu_language) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS).also {
                startActivity(it)
            }
        }

        if (item.itemId == R.id.menu_favorite) {
            val intent = Intent(this, FavoriteActivity::class.java).also {
                startActivity(it)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun handlekeyIntent() {
        username = intent.getStringExtra(USERNAME_KEY) as String
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, viewModelFactory)[UserDetailViewModel::class.java]
    }




    private fun initObserver() {
        viewModel.state.observe(this, Observer {
            handleStateLoading(it)
        })

        viewModel.resultUserDetail.observe(this, Observer {
            handleUserDetail(it)
        })
    }

    private fun handleUserDetail(data: UserDetails) {
        userDetail = data
        txt_username.text  = data.login
        txt_bio.text = data.bio
        txt_follower.text = data.followers.toString()
        txt_following.text = data.following.toString()
        txt_repo.text = data.publicRepos.toString()
        Glide.with(this).load(data.avatarUrl).circleCrop().into(img_detail_user)
    }

    private fun handleStateLoading(loading: LoaderState) {
        if (loading is LoaderState.showLoading) {
            fav_button.setGONE()
        } else {
            fav_button.setVisible()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}