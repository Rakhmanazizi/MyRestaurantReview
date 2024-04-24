package com.example.myrestaurantreview.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myrestaurantreview.data.RestaurantRepository
import com.example.myrestaurantreview.di.Injection
import com.example.myrestaurantreview.ui.detail.DetailViewModel
import com.example.myrestaurantreview.ui.main.MainViewModel

class ViewModelFactory private constructor(
    private val repository: RestaurantRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> return MainViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException(
            "Unknown ViewModel Class: " +
                    modelClass.name
        )
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository())
        }
    }
}