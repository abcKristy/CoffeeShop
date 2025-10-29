package com.example.coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeshop.Activity.DetailActivity
import com.example.coffeeshop.Domain.ItemsModel
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ViewholderPopularBinding

class PopularAdapter(val items: MutableList<ItemsModel>):
    RecyclerView.Adapter<PopularAdapter.Viewholder>() {

    lateinit var context: Context
    class Viewholder(val binding: ViewholderPopularBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$" + items[position].price.toString()

        val picUrls = items[position].picUrl
        if (picUrls.isNotEmpty()) {
            Glide.with(context)
                .load(picUrls[0])
                .into(holder.binding.pic)
        } else {
            // Установите placeholder или скройте изображение
            Glide.with(context)
                .load(R.drawable.close) // добавьте placeholder
                .into(holder.binding.pic)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object",items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

}