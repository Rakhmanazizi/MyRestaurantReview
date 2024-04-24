package com.example.myrestaurantreview.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(

    @field:SerializedName("restaurant")
    val restaurant: Restaurant? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class CategoriesItem(

    @field:SerializedName("name")
    val name: String? = null
)

data class Restaurant(

    @field:SerializedName("customerReviews")
    val customerReviews: List<CustomerReviewsItem?>? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("pictureId")
    val pictureId: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("categories")
    val categories: List<CategoriesItem?>? = null,

    @field:SerializedName("menus")
    val menus: Menus? = null
)

data class Menus(

    @field:SerializedName("foods")
    val foods: List<FoodsItem?>? = null,

    @field:SerializedName("drinks")
    val drinks: List<DrinksItem?>? = null
)

data class DrinksItem(

    @field:SerializedName("name")
    val name: String? = null
)

data class CustomerReviewsItem(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("review")
    val review: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)

data class FoodsItem(

    @field:SerializedName("name")
    val name: String? = null
)

data class PostReviewResponse(
    @field:SerializedName("customerReviews")
    val customerReviews: List<CustomerReviewsItem?>? = null,
    @field:SerializedName("error")
    val error: Boolean? = null,
    @field:SerializedName("message")
    val message: String? = null
)
