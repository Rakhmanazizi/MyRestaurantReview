package com.example.myrestaurantreview.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrestaurantreview.data.model.RestaurantsItem
import com.example.myrestaurantreview.databinding.ItemRestaurantBinding

class RestaurantListAdapter :
    ListAdapter<RestaurantsItem,
            RestaurantListAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(restaurantId: String?)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(private var binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantsItem) {
            binding.apply {
                tvItemName.text = restaurant.name
                tvItemCity.text = restaurant.city
                Glide.with(itemView.context)
                    .load(("https://restaurant-api.dicoding.dev/images/medium/${restaurant.pictureId}")
                    )
                    .into(ivItemPhoto)
            }
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(restaurant.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.setIsRecyclable(false)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RestaurantsItem>() {
            override fun areItemsTheSame(oldItem: RestaurantsItem, newItem: RestaurantsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RestaurantsItem, newItem: RestaurantsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
