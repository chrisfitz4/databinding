package com.illicitintelligence.android.databindingrv.view

import android.app.Application
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.request.RequestOptions
import com.illicitintelligence.android.databindingrv.R
import com.illicitintelligence.android.databindingrv.model.PicSumModel
import kotlinx.android.synthetic.main.dogs.view.*

class DogAdapter(val context: Application): RecyclerView.Adapter<DogAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    var myList: List<PicSumModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val myView : View = LayoutInflater.from(parent.context).inflate(R.layout.dogs,parent,false)
        return MyViewHolder(myView)
    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("TAG_X","${myList[position].author}")
        Glide.with(context)
            .load(myList[position].url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.itemView.picSumImage)
    }


}