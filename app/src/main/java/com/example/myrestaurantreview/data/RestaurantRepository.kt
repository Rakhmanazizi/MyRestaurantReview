package com.example.myrestaurantreview.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.myrestaurantreview.data.model.RestaurantDetailResponse
import com.example.myrestaurantreview.data.model.RestaurantResponse
import com.example.myrestaurantreview.data.network.ApiServices
import kotlinx.coroutines.Dispatchers

class RestaurantRepository private constructor(
    private val apiServices: ApiServices
) {
    fun getRestaurantList(): LiveData<Result<RestaurantResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiServices.getRestaurantList()
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun getRestaurantDetail(id: String): LiveData<Result<RestaurantDetailResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiServices.getRestaurantDetail(id)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: RestaurantRepository? = null
        fun getInstance(
            apiServices: ApiServices,
        ): RestaurantRepository = instance ?: synchronized(this) {
            instance ?: RestaurantRepository(apiServices)
        }.also { instance = it }
    }
}