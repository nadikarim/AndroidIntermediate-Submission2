package com.nadikarim.submission2.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nadikarim.submission2.R
import com.nadikarim.submission2.data.ListAdapter
import com.nadikarim.submission2.data.LoadingStateAdapter
import com.nadikarim.submission2.databinding.ActivityMainBinding
import com.nadikarim.submission2.ui.login.LoginActivity
import com.nadikarim.submission2.ui.maps.MapsActivity
import com.nadikarim.submission2.ui.story.add.AddStoryActivity
import com.nadikarim.submission2.utils.DataStoreViewModel
import com.uk.tastytoasty.TastyToasty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainVIewModel>()
    private lateinit var binding: ActivityMainBinding
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()
    private lateinit var adapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"

        adapter = ListAdapter()

        setupViewModel()
        setRecyclerView()
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


    private fun setupViewModel() {
        dataStoreViewModel.getSession().observe(this) { userSession ->
            if (!userSession.isLogin) {
                TastyToasty.success(this, "token tidak ada, login dulu yuk").show()
                Log.d("tag", userSession.token)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity as Activity).toBundle()
                )
                finish()
            } else {
                viewModel.story.observe(this) {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
        viewModel.story.observe(this) {
            adapter.submitData(lifecycle, it)
        }

        viewModel.isLoading.observe(this) { showLoading(it) }
    }

    private fun setRecyclerView() {
        binding.apply {
            rvStory.layoutManager = LinearLayoutManager(this@MainActivity)
            rvStory.setHasFixedSize(true)
            rvStory.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
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
                //mLoginPreference.logout()
                dataStoreViewModel.logout()
                Log.d("tag", "token dihapus")
                //Log.d("tag", mLoginPreference.getUser().isLogin.toString())
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            R.id.menu_maps -> {
                startActivity(Intent(this, MapsActivity::class.java))
                true
            }
            else -> {return super.onOptionsItemSelected(item)}
        }
    }
}