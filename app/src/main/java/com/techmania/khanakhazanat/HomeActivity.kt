package com.techmania.khanakhazanat

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.techmania.khanakhazanat.databinding.ActivityHomeBinding
import com.techmania.khanakhazanat.databinding.PopularRvItemBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList :ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    binding.search.setOnClickListener {
      startActivity  (Intent(this,SearchActivity::class.java))
    }
    binding.salad.setOnClickListener{
        val myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
        myIntent.putExtra("TITTLE","Salad")
        myIntent.putExtra("CATEGORY","Salad")
        startActivity(myIntent)
    }
    binding.MainDish.setOnClickListener{
        val myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
        myIntent.putExtra("TITTLE","Main Dish")
        myIntent.putExtra("CATEGORY","Dish")
        startActivity(myIntent)
    }
    binding.Drinks.setOnClickListener{
        val myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
        myIntent.putExtra("TITTLE","Drinks")
        myIntent.putExtra("CATEGORY","Drinks")
        startActivity(myIntent)
    }
    binding.Dessert.setOnClickListener{
        val myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
        myIntent.putExtra("TITTLE","Desserts")
        myIntent.putExtra("CATEGORY","Desserts")
        startActivity(myIntent)
    }
        binding.more.setOnClickListener{
            var dailog = Dialog(this)
            dailog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dailog.setContentView(R.layout.bottom_sheet)

            dailog.show()
            dailog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dailog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dailog.window!!.setGravity(Gravity.BOTTOM)

        }

    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db = Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for (i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            rvAdapter = PopularAdapter(dataList,this)
            binding.rvPopular.adapter=rvAdapter
        }
    }
}