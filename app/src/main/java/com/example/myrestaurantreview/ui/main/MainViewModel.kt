package com.example.myrestaurantreview.ui.main

import androidx.lifecycle.ViewModel
import com.example.myrestaurantreview.data.RestaurantRepository

class MainViewModel(private val repository: RestaurantRepository): ViewModel() {
    fun getRestaurantList() = repository.getRestaurantList()
}