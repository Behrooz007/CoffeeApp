package com.example.coffeeb.Activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeb.Adapters.CartAdapter
import com.example.coffeeb.Local.Database.CartDatabase
import com.example.coffeeb.R
import com.example.coffeeb.Repository.CartRepository
import com.example.coffeeb.ViewModel.CartViewModel
import com.example.coffeeb.ViewModel.CartViewModelFactory
import com.example.coffeeb.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var viewModel: CartViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dao = CartDatabase.getDatabase(application).cartDao
        val repository = CartRepository(dao)
        val factory = CartViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]

        //cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        viewModel.allItems.observe(this) { items ->
            adapter = CartAdapter(items, viewModel)
            adapter.notifyDataSetChanged()
            binding.cartListView.layoutManager = LinearLayoutManager(this)
            binding.cartListView.adapter = adapter
            Log.v("Title", items.toString())
        }

        binding.btnCart.setOnClickListener {
            Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show()
        }
        binding.backBtn.setOnClickListener {
            finish()
        }





    }
}