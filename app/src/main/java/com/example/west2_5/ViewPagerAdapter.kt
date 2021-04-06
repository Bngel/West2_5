package com.example.west2_5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val images: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemImg: ImageView = view.findViewById(R.id.item_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = position % 4
        holder.itemImg.setImageResource(images[pos])
    }

    override fun getItemCount() =Int.MAX_VALUE
}