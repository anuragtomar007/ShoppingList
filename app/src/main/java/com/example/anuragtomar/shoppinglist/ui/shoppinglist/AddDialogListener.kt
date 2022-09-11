package com.example.anuragtomar.shoppinglist.ui.shoppinglist

import com.example.anuragtomar.shoppinglist.data.db.entity.ShoppingItem

interface AddDialogListener {
    fun onAddButtonCLicked(item: ShoppingItem)
}