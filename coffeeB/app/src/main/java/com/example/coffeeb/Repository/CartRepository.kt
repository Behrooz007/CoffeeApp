package com.example.coffeeb.Repository

import androidx.lifecycle.LiveData
import com.example.coffeeb.Local.Enteties.CartEnteties
import com.example.coffeeb.Local.dao.cartDao

class CartRepository(private val localDao: cartDao) {

    suspend fun insertItem(item:CartEnteties){
        localDao.insertItem(item)
    }

    suspend fun deleteItem(item: CartEnteties){
        return localDao.deleteItem(item)
    }

    suspend fun updateItem(item: CartEnteties){
       return localDao.updateItem(item)
    }

    fun getAllItems():LiveData<List<CartEnteties>>{
        return localDao.getAllItems()
    }

}