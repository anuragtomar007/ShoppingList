package com.example.anuragtomar.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.anuragtomar.shoppinglist.data.db.entity.ShoppingItem

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(shoppingItem: ShoppingItem)

    @Delete
    suspend fun delete(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}