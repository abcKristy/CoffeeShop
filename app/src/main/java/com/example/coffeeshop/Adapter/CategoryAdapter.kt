package com.example.coffeeshop.Adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.Activity.ItemListActivity
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items: MutableList<CategoryModel>)
    : RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    inner class Viewholder(val binding: ViewholderCategoryBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Viewholder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(holder.itemView.context, ItemListActivity::class.java).apply{
                        putExtra("id", item.id.toString())
                        putExtra("title",item.title)
                    }
                    ContextCompat.startActivity(holder.itemView.context,intent,null)
                },500)
            }
        }

        if (selectedPosition == position) {
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_bg)
            holder.binding.titleCat.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_bg)
            holder.binding.titleCat.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int = items.size

}