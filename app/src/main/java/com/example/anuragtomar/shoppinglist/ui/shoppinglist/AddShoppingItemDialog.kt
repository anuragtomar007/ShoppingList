package com.example.anuragtomar.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.anuragtomar.shoppinglist.R
import com.example.anuragtomar.shoppinglist.data.db.entity.ShoppingItem

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        val tvAdd: TextView? = findViewById(R.id.tvAdd)
        val tvCancel: TextView? = findViewById(R.id.tvCancel)
        val etName: EditText? = findViewById(R.id.etName)
        val etAmount: EditText? = findViewById(R.id.etAmount)

        tvAdd?.setOnClickListener {
            val name = etName?.text.toString()
            val amount = etAmount?.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(
                    context,
                    "Please provide all the necessary details",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonCLicked(item)
            dismiss()
        }

        tvCancel?.setOnClickListener {
            cancel()
        }
    }
}