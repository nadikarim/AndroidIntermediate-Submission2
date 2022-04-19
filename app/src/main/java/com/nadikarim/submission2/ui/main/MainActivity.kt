package com.nadikarim.submission2.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadikarim.submission2.R
import com.nadikarim.submission2.data.ListAdapter
import com.nadikarim.submission2.databinding.ActivityMainBinding
import com.nadikarim.submission2.ui.login.LoginActivity
import com.nadikarim.submission2.ui.story.add.AddStoryActivity
import com.nadikarim.submission2.utils.LoginPreference
import com.nadikarim.submission2.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainVIewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var mLoginPreference: LoginPreference
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"

        mLoginPreference = LoginPreference(this)
        adapter = ListAdapter()

        setupViewModel()
        setRecyclerView()
        validate()
        action()
    }

    private fun action() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddStoryActivity::class.java)
            startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle()
            )
        }
    }

    private fun validate() {
        if (!mLoginPreference.getUser().isLogin) {
            val login = mLoginPreference.getUser().isLogin
            Log.d("tag", login.toString())
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle()
            )
            finish()
        }
    }

    private fun setupViewModel() {
        viewModel.getStories(mLoginPreference.getUser().token)
        /*
        viewModel.listStory.observe(this) {
            if (it != null) {
                adapter.submitData(lifecycle, it)
            }
        }

         */
        viewModel.quote(mLoginPreference.getUser().token).observe(this) {
            if (it != null) {
                adapter.submitData(lifecycle, it)
            }
        }
        viewModel.isLoading.observe(this) { showLoading(it) }
        /*
        viewModel.getUserSession().observe(this) {
            if (it.isLogin) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle()
                )
                finish()
            }
        }

         */
        viewModel.getStories("Bearer ${mLoginPreference.getUser().token}")
    }

    private fun setRecyclerView() {
        binding.apply {
            rvStory.layoutManager = LinearLayoutManager(this@MainActivity)
            rvStory.setHasFixedSize(true)
            rvStory.adapter = adapter

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.menu_logout -> {
                mLoginPreference.logout()
                //viewModel.logout()
                Log.d("tag", "token dihapus")
                Log.d("tag", mLoginPreference.getUser().isLogin.toString())
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }
}