package com.example.coffeeb.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coffeeb.Model.BannerModel
import com.example.coffeeb.Model.ItemsModel
import com.example.coffeeb.Model.modelCategory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainRepository {



    private val databaseRef = FirebaseDatabase.getInstance()


    //fetch banner
    fun fetchBanners(): LiveData<List<BannerModel>> {
        val liveData = MutableLiveData<List<BannerModel>>()

        databaseRef.getReference("Banner").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val banners = mutableListOf<BannerModel>()
                for (child in snapshot.children) {
                    val banner = child.getValue(BannerModel::class.java)
                    banner?.let { banners.add(it) }
                }
                liveData.value = banners
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.value = emptyList()
            }
        })

        return liveData
    }

    //fetch Category

    fun fetchCategory():LiveData<List<modelCategory>> {

        val liveData = MutableLiveData<List<modelCategory>>()
        databaseRef.getReference("Category").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<modelCategory>()

                for(child in snapshot.children) {

                    val banner = child.getValue(modelCategory::class.java)
                    banner?.let { categoryList.add(it) }
                }
                liveData.value = categoryList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                liveData.value = emptyList()
            }

        })

        return liveData
    }

    //fetch Category

    fun fetchItems():LiveData<List<ItemsModel>> {

        val liveData = MutableLiveData<List<ItemsModel>>()
        databaseRef.getReference("Popular").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemsList = mutableListOf<ItemsModel>()

                for(child in snapshot.children) {

                    val banner = child.getValue(ItemsModel::class.java)
                    banner?.let { itemsList.add(it) }
                }
                liveData.value = itemsList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
                liveData.value = emptyList()
            }

        })

        return liveData
    }

}