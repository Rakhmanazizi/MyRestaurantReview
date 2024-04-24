package com.example.myrestaurantreview.data.network

import com.example.myrestaurantreview.data.model.RestaurantDetailResponse
import com.example.myrestaurantreview.data.model.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("list")
    suspend fun getRestaurantList(): RestaurantResponse

    @GET("detail/{id}")
    suspend fun getRestaurantDetail(
        @Path("id") id: String
    ): RestaurantDetailResponse
}