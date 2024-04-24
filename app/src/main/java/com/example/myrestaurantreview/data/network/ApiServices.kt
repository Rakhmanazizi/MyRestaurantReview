package com.example.myrestaurantreview.data.network

import com.example.myrestaurantreview.data.model.PostReviewResponse
import com.example.myrestaurantreview.data.model.RestaurantDetailResponse
import com.example.myrestaurantreview.data.model.RestaurantResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @GET("list")
    suspend fun getRestaurantList(): RestaurantResponse

    @GET("detail/{id}")
    suspend fun getRestaurantDetail(
        @Path("id") id: String
    ): RestaurantDetailResponse

    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    suspend fun postReview(
        @Field("id") id: String,
        @Field("name") name: String,
        @Field("review") review: String
    ): PostReviewResponse
}