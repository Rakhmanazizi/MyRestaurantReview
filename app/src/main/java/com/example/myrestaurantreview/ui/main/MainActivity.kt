package com.example.myrestaurantreview.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrestaurantreview.R
import com.example.myrestaurantreview.data.model.RestaurantsItem
import com.example.myrestaurantreview.databinding.ActivityMainBinding
import com.example.myrestaurantreview.util.ViewModelFactory
import com.example.myrestaurantreview.data.Result
import com.example.myrestaurantreview.ui.detail.DetailActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvRestaurant.layoutManager = layoutManager

        mainViewModel.getRestaurantList().observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Error: ${result.error}", Toast.LENGTH_SHORT).show()
                }

                is Result.Success -> {
                    showLoading(false)
                    setRestaurantList(result.data.restaurants)
                }
            }
        }
    }

    private fun setRestaurantList(restaurantList: List<RestaurantsItem?>?) {
        binding.rvRestaurant.adapter = RestaurantListAdapter().apply {
            setOnItemClickCallback(object :
                RestaurantListAdapter.OnItemClickCallback {
                override fun onItemClicked(restaurantId: String?) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java).putExtra(
                        DetailActivity.EXTRA_ID,
                        restaurantId
                    )
                    startActivity(intent)
                }
            })
            submitList(restaurantList)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility =  if (isLoading) View.VISIBLE else View.GONE
    }
}