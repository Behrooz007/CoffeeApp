package com.example.coffeeb.Local.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffeeb.Local.Enteties.CartEnteties
import com.example.coffeeb.Local.dao.cartDao


@Database(entities = [CartEnteties::class], version = 1)
abstract class CartDatabase : RoomDatabase() {

    abstract val cartDao: cartDao

    //Singleton design pattern

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null

        fun getDatabase(context: Context): CartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "CartDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}