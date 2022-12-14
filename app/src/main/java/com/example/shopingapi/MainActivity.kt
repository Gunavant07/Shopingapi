package com.example.shopingapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.shopingapi.ModelData.ModelData
import com.example.shopingapi.adapter.StoreAdapter
import com.example.shopingapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var storeAdapter: StoreAdapter
    var list = arrayListOf<ModelData>()
    var apiStoreLink = "https://fakestoreapi.com/products"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClick()
        apiLink()



    }

    private fun initClick() {

    }
    fun rvSetUp() {

        storeAdapter = StoreAdapter(this, list)
        var lm = GridLayoutManager(this,2)
        binding.rcy.layoutManager = lm
        binding.rcy.adapter = storeAdapter
    }
    fun apiLink() {

        var jsonArrayRequest =
            JsonArrayRequest(Request.Method.GET, apiStoreLink, null, {
                var i = 0;
                while (i < it.length()) {
                    var jsonObject = it.getJSONObject(i)
                    var id = jsonObject.getString("id")
                    var title = jsonObject.getString("title")
                    var price = jsonObject.getString("price")
                    var description = jsonObject.getString("description")
                    var category = jsonObject.getString("category")
                    var image = jsonObject.getString("image")
                    var rating = jsonObject.getString("rating")


                    var storeModel = ModelData(id,title,price,description,category,image,rating)
                    list.add(storeModel)

                    Log.e("TAG", "apiLink: ${list[i].id}  ${list[i].title}  ${list[i].price}" )
                    i++
                }


                rvSetUp()
            },
                {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                })
        var requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonArrayRequest)
    }

}