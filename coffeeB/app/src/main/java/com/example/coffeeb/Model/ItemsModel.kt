package com.example.coffeeb.Model

import java.io.Serializable

data class ItemsModel(
    var title: String= "",
    val description:String = "",
    val picUrl:ArrayList<String> = ArrayList(),
    val price: Double = 0.0,
    var rating:Double = 0.0,
    var numberInCart: Int  = 0,
    var extra:String = ""

): Serializable
