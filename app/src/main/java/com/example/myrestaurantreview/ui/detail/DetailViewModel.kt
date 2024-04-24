package com.example.myrestaurantreview.ui.detail

import androidx.lifecycle.ViewModel
import com.example.myrestaurantreview.data.RestaurantRepository

class DetailViewModel(private val repository: RestaurantRepository): ViewModel() {

    fun getRestaurantDetail(id: String) = repository.getRestaurantDetail(id)

    fun postReview(id: String, name: String, review: String) = repository.postReview(id, name, review)

}