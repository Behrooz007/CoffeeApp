package com.example.coffeeb.Local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.coffeeb.Local.Enteties.CartEnteties

@Dao
interface cartDao {
    @Insert
    suspend fun insertItem(item:CartEnteties)

    @Update
    suspend fun updateItem(item: CartEnteties)

    @Delete
    suspend fun deleteItem(item: CartEnteties)

    @Query("Select * From cartTable")
    fun getAllItems(): LiveData<List<CartEnteties>>
}