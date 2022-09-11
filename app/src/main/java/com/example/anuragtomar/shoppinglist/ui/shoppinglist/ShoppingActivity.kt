package com.example.anuragtomar.shoppinglist.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anuragtomar.shoppinglist.R
import com.example.anuragtomar.shoppinglist.data.db.entity.ShoppingItem
import com.example.anuragtomar.shoppinglist.other.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val shoppingViewModel =
            ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val recyclerView: RecyclerView? = findViewById(R.id.rvShoppingItems)
        val fab: FloatingActionButton? = findViewById(R.id.fab)

        recyclerView?.layoutManager = LinearLayoutManager(this)

        val adapter = ShoppingItemAdapter(listOf(), shoppingViewModel)
        recyclerView?.adapter = adapter

        shoppingViewModel.getAllShoppingItems().observe(this, Observer {
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })

        fab?.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener {
                override fun onAddButtonCLicked(item: ShoppingItem) {
                    shoppingViewModel.upsert(item)
                }
            }).show()
        }
    }
}