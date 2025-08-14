package com.example.coffeeb.Adapters

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.coffeeb.Model.modelCategory
import com.example.coffeeb.R
import com.example.coffeeb.databinding.CategoryViewholderBinding



class CategoryAdapter(val items: List<modelCategory>): RecyclerView.Adapter<CategoryAdapter.ViewHodler>()  {

        lateinit var context:Context
        private var selectedPosition = -1
        private var lastSelectedPosition = -1

        inner class ViewHodler(val binding: CategoryViewholderBinding) : RecyclerView.ViewHolder(binding.root) {


        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHodler {

        context = parent.context
        val binding = CategoryViewholderBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHodler(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHodler, position: Int) {
        val items = items[position]
        holder.binding.titleCat.text = items.title

        holder.binding.root.setOnClickListener({
            lastSelectedPosition = selectedPosition
            selectedPosition = position

            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({
            },500)

            if(selectedPosition == position){
                holder.binding.titleCat.setBackgroundResource(R.drawable.grey_full_corner)
                holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
            }else {
                holder.binding.titleCat.setBackgroundResource(R.drawable.white_bg)
                holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.darkBrown))
            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
}