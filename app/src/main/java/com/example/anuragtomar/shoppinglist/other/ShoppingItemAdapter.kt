package com.example.anuragtomar.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.anuragtomar.shoppinglist.R
import com.example.anuragtomar.shoppinglist.data.db.entity.ShoppingItem
import com.example.anuragtomar.shoppinglist.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var itemList: List<ShoppingItem>,
    private val shoppingViewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = itemList[position]

        holder.tvName.text = currentShoppingItem.name
        holder.tvAmount.text = "${currentShoppingItem.amount}"

        holder.ivDelete.setOnClickListener {
            shoppingViewModel.delete(currentShoppingItem)
        }

        holder.ivPlus.setOnClickListener {
            currentShoppingItem.amount++
            shoppingViewModel.upsert(currentShoppingItem)
        }

        holder.ivMinus.setOnClickListener {
            if (currentShoppingItem.amount > 0) {
                currentShoppingItem.amount--
                shoppingViewModel.upsert(currentShoppingItem)
            }
        }
    }


    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val ivPlus: ImageView = itemView.findViewById(R.id.ivPlus)
        val ivMinus: ImageView = itemView.findViewById(R.id.ivMinus)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
    }
}