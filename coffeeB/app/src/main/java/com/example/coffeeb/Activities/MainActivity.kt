package com.example.coffeeb.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeeb.Adapters.CategoryAdapter
import com.example.coffeeb.Adapters.PopulerAdapter
import com.example.coffeeb.Local.Database.CartDatabase
import com.example.coffeeb.Model.modelCategory
import com.example.coffeeb.R
import com.example.coffeeb.Repository.CartRepository
import com.example.coffeeb.ViewModel.CartViewModel
import com.example.coffeeb.ViewModel.CartViewModelFactory
import com.example.coffeeb.ViewModel.myViewModel
import com.example.coffeeb.databinding.ActivityMainBinding
import com.example.coffeeb.databinding.ActivitySignUpBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: myViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var  bannerImg:ImageView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var viewModelCart: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bannerImg = findViewById<ImageView>(R.id.banner)

        viewModel = ViewModelProvider(this).get(myViewModel::class.java)

        val dao = CartDatabase.getDatabase(application).cartDao
        val repository = CartRepository(dao)
        val factory = CartViewModelFactory(repository)

        viewModelCart = ViewModelProvider(this, factory)[CartViewModel::class.java]

        initBanner()
        initCategory()
        initPopular()
        intiBottomNav()



    }

    private fun intiBottomNav(){
        binding.cartBtnBottomNav.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)

        }
    }

    private fun initPopular() {

        binding.progressBarPopuler.visibility = View.VISIBLE

        viewModel.popularList.observe(this,) { popularList ->

            if(popularList.isNotEmpty()){
                binding.recyclerViewPopuler.layoutManager = GridLayoutManager(this, 2)
                binding.recyclerViewPopuler.adapter = PopulerAdapter(popularList, viewModelCart)
                binding.progressBarPopuler.visibility = View.GONE
            }
        }

        
    }

    private fun initBanner(){
        bannerImg = findViewById(R.id.banner)
        viewModel.bannerList.observe(this) { banners ->

            if (banners.isNotEmpty()) {
                binding.progressBarBanner.visibility = View.INVISIBLE
                val firstBanner = banners[0]
                Log.v("Bal",firstBanner.url)
                Glide.with(this)
                    .load(firstBanner.url)
                    .into(bannerImg)


            }
        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity,
            LinearLayoutManager.HORIZONTAL,false)

        // Init adapter
        categoryAdapter = CategoryAdapter(emptyList())
        binding.categoryRecyclerView.adapter = categoryAdapter

        // Observe data from ViewModel
        viewModel.categoryList.observe(this) { categories ->
            if (categories.isNotEmpty()) {
                binding.progressBarCategory.visibility = View.GONE
                val adapter = CategoryAdapter(categories)
                binding.categoryRecyclerView.adapter = adapter
                binding.progressBarCategory.visibility = View.GONE
            }

        }


    viewModel.categoryList
    }

}