package com.example.shopingapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.shopingapi.MainActivity
import com.example.shopingapi.ModelData.ModelData
import com.example.shopingapi.R

class StoreAdapter(val mainActivity: MainActivity,val list: ArrayList<ModelData>) : RecyclerView.Adapter<StoreAdapter.ViewData>() {
    class ViewData(itemview : View):ViewHolder(itemview){

        var img =itemview.findViewById<ImageView>(R.id.img)
        var title =itemview.findViewById<TextView>(R.id.txttitle)
        var price =itemview.findViewById<TextView>(R.id.price)
        var category =itemview.findViewById<TextView>(R.id.category)
        var description =itemview.findViewById<TextView>(R.id.description)
        var rating =itemview.findViewById<TextView>(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =LayoutInflater.from(mainActivity).inflate(R.layout.itemview,parent,false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        holder.title.setText(list[position].title).toString()
        holder.price.setText(list[position].price).toString()
        holder.category.setText(list[position].category).toString()
        holder.description.setText(list[position].description).toString()
        holder.rating.setText(list[position].rating).toString()
        Glide.with(mainActivity).load(list[position].image).into(holder.img)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}