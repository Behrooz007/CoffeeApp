package com.example.coffeeb.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeb.Model.BannerModel
import com.example.coffeeb.Model.ItemsModel
import com.example.coffeeb.Model.modelCategory
import com.example.coffeeb.Repository.MainRepository
import java.util.Locale.Category


class myViewModel: ViewModel() {

    private val repository = MainRepository()
    val bannerList: LiveData<List<BannerModel>> = repository.fetchBanners()
    val categoryList:LiveData<List<modelCategory>> = repository.fetchCategory()
    val popularList:LiveData<List<ItemsModel>> = repository.fetchItems()
}