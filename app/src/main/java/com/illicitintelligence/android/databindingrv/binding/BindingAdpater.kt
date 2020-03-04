package com.illicitintelligence.android.databindingrv.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.illicitintelligence.android.databindingrv.R
import com.illicitintelligence.android.databindingrv.view.DogAdapter

@BindingAdapter("img")
fun loadImage(imageView: ImageView, imageUrl: String?){
    Glide.with(imageView.context).load(imageUrl)
        .placeholder(R.drawable.ic_launcher_foreground).into(imageView)
}

@BindingAdapter("the_adapter")
fun setAdapter(view: RecyclerView, adapter: DogAdapter?){
    Log.d("TAG_X", "setAdapter: ${adapter==null}, ${adapter is DogAdapter}")
    view.adapter = adapter
}