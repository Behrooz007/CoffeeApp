package com.example.coffeeb.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coffeeb.Local.Enteties.CartEnteties
import com.example.coffeeb.ViewModel.CartViewModel
import com.example.coffeeb.databinding.ViewholderCartBinding
import com.example.coffeeb.databinding.ViewholderPopulerBinding

class CartAdapter(private val items: List<CartEnteties>,
                  private val viewModel: CartViewModel
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    lateinit var context: Context
    inner class ViewHolder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item = items[position]

        holder.binding.priceTxtCart.text = "$${item.price}"
        holder.binding.titleCart.text = item.title
        holder.binding.productQuantity.text = item.quantity.toString()

        Glide.with(context)
            .load(item.url)
            .into(holder.binding.productImgCart)

        holder.binding.minusCart.setOnClickListener({
            if (item.quantity > 1) {
                item.quantity--
                holder.binding.productQuantity.text = item.quantity.toString()
                viewModel.updateItem(item) // Save to DB
            }else{
                viewModel.deleteItem(item)
            }

        })

        holder.binding.plusCart.setOnClickListener({

            item.quantity++
            holder.binding.productQuantity.text = item.quantity.toString()
            viewModel.updateItem(item) // Save to DB
        })



    }


}