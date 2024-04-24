package com.example.myrestaurantreview.ui.detail

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myrestaurantreview.R
import com.example.myrestaurantreview.databinding.ActivityDetailBinding
import com.example.myrestaurantreview.util.ViewModelFactory
import com.example.myrestaurantreview.data.Result
import com.example.myrestaurantreview.data.model.CustomerReviewsItem
import com.example.myrestaurantreview.data.model.Restaurant

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Restaurant Detail"

        val layoutManager = LinearLayoutManager(this)
        binding.rvReview.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvReview.addItemDecoration(itemDecoration)

        val id = intent.getStringExtra(EXTRA_ID)
        Log.d("DetailActivity", "ID: $id")

        if (id != null) {
            detailViewModel.getRestaurantDetail(id).observe(this) { result ->
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
                        setRestaurantDetail(result.data.restaurant)
                        setRestaurantReview(result.data.restaurant?.customerReviews)
                    }
                }
            }
            binding.btnSend.setOnClickListener {
                val review = binding.edReview.text.toString()
                postReview(id, "Farhan", review)
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }

    private fun postReview(id: String, name: String, review: String) {
        detailViewModel.postReview(id, name, review).observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Error -> {
                    showLoading(false)
                }

                is Result.Success -> {
                    showLoading(false)
                    setRestaurantReview(result.data.customerReviews)
                }
            }
        }
    }

    private fun setRestaurantReview(customerReview: List<CustomerReviewsItem?>?) {
        val adapter = ReviewListAdapter()
        adapter.submitList(customerReview)
        binding.rvReview.adapter = adapter
        binding.edReview.setText("")
    }

    private fun setRestaurantDetail(restaurant: Restaurant?) {
        restaurant?.let {
            binding.apply {
                Glide.with(this@DetailActivity)
                    .load("https://restaurant-api.dicoding.dev/images/large/${it.pictureId}")
                    .into(ivPhoto)
                tvName.text = it.name
                tvDescription.text = it.name
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}