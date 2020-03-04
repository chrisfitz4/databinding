package com.illicitintelligence.android.databindingrv.network

import com.illicitintelligence.android.databindingrv.model.PicSumModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesService {

    @GET("/v2/list")
    fun getPicturesList(): Observable<List<PicSumModel>>

    @GET("/v2/list")
    fun getPicturesPage(
        @Query("page")page:Int):Observable<List<PicSumModel>>
}