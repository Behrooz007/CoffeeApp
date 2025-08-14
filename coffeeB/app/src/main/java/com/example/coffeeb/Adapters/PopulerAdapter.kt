package com.example.coffeeb.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeb.Local.Enteties.CartEnteties
import com.example.coffeeb.Model.ItemsModel
import com.example.coffeeb.ViewModel.CartViewModel
import com.example.coffeeb.databinding.ViewholderPopulerBinding

class PopulerAdapter(val items: List<ItemsModel>
                     ,
                     private val viewModel: CartViewModel
): RecyclerView.Adapter<PopulerAdapter.ViewHolder>() {

    private lateinit var context:Context
    inner class ViewHolder(val binding: ViewholderPopulerBinding) :
        RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulerAdapter.ViewHolder {
        val binding = ViewholderPopulerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopulerAdapter.ViewHolder, position: Int) {
        var item = items[position]
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$"+items[position].price.toString()
        holder.binding.subTitleTxt.text = items[position].extra

        Glide.with(context).
            load(items[position
            ].picUrl[0]).into(holder.binding.imgView);

        holder.binding.addBtn.setOnClickListener({

            val cartItem = CartEnteties(
                title = item.title,
                price = item.price,
                quantity = 1,
                url = item.picUrl[0]
            )
            viewModel.insetItem(cartItem)

        })


    }


    override fun getItemCount(): Int {
        return items.size
    }

}