package com.example.taskapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskapp.R
import com.example.taskapp.databinding.ItemPhoneBinding
import com.example.taskapp.models.Offer

class PhoneAdapter : RecyclerView.Adapter<PhoneAdapter.MyViewHolder>() {

    val list = ArrayList<Offer>()

    fun addFile(offer: Offer) {
        val old = list.size
        list.add(offer)
        notifyItemInserted(old)
        notifyItemRangeChanged(old, list.size - 1)
    }

    inner class MyViewHolder(private val binding: ItemPhoneBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind() {

            val model = list[adapterPosition]
            binding.tvName.text = "Name: " + model.name
            binding.tvBrand.text = "Brand: " + model.brand
            binding.tvCategory.text = "Category: " + model.category
            binding.tvMerchant.text = "Merchant: " + model.merchant

            binding.ivImage.layoutParams.width = model.image.width
            binding.ivImage.layoutParams.height = model.image.height
            Glide.with(binding.root).load(model.image.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivImage)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemPhoneBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind()
    }
}