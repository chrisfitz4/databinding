package com.illicitintelligence.android.databindingrv.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.illicitintelligence.android.databindingrv.model.PicSumModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {
    private var picturesService : PicturesService

    init {
        picturesService = createService(provideRestClient().createRetrofitAdapter())
    }

    fun provideRestClient() =
        RestClient(RestClientConfig(
            provideGsonConverterFactory(),
            provideRxJava2CallAdapterFactory()))

    fun getPictures(): Observable<List<PicSumModel>>{
        return picturesService.getPicturesList()
    }

    fun getPicturesPage(page:Int):Observable<List<PicSumModel>>{
        return picturesService.getPicturesPage(page)
    }

    private fun createService(retrofit: Retrofit):PicturesService{
        return retrofit.create(PicturesService::class.java)
    }

    private fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .serializeNulls()
        .create()

    private fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(provideGson())

    private fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
}

class RestClient(private val restClientConfig: RestClientConfig) {
    fun createRetrofitAdapter(hostUrl: String = "https://picsum.photos"): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(restClientConfig.callAdapterFactory)
        .addConverterFactory(restClientConfig.converterFactory)
        .baseUrl(hostUrl)
        .build()
}

data class RestClientConfig(
    val converterFactory: Converter.Factory,
    val callAdapterFactory: CallAdapter.Factory) {
    private var interceptors: MutableList<Interceptor> = mutableListOf()

    fun addInterceptor(interceptor: Interceptor) = interceptors.add(interceptor)

    fun interceptors() = interceptors.asIterable()
}