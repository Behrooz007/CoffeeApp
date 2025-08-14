package com.example.coffeeb.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeb.Local.Enteties.CartEnteties
import com.example.coffeeb.Repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository): ViewModel() {

    val allItems: LiveData<List<CartEnteties>> = repository.getAllItems()

    fun insetItem(item: CartEnteties ) {
        viewModelScope.launch {
            repository.insertItem(item)
        }
    }

    fun deleteItem(item: CartEnteties ) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun updateItem(item: CartEnteties){

        viewModelScope.launch {
            repository.updateItem(item)
        }

    }



}