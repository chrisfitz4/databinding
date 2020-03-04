package com.illicitintelligence.android.databindingrv.model

import com.google.gson.annotations.SerializedName

data class PicSumModel(
    val id: String,
    val author: String,
    @SerializedName("download_url")
    val url: String,
    val width: Int,
    val height: Int
)