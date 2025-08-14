package com.example.coffeeb.Local.Enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cartTable")
data class CartEnteties(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val url: String = "",
    val title:String = "",
    val price: Double,
    var quantity: Int

): Serializable
