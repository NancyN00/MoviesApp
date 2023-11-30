package com.example.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.viewmodel.ShowViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel : ShowViewModel by viewModels()
    private lateinit var adapter : ShowsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
        getShows()


    }

    private fun getShows() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->

                    if (state.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    if (state.shows.isNotEmpty()) {
                        binding.progressBar.visibility = View.GONE
                        adapter.setShowsList(state.shows)
                    }

                    if (state.message.isNotBlank()) {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }

    private fun setUpUi() {
        adapter = ShowsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}