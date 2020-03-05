package com.illicitintelligence.android.databindingrv.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.illicitintelligence.android.databindingrv.network.NetworkProvider
import com.illicitintelligence.android.databindingrv.view.DogAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PicViewModel(application: Application) : AndroidViewModel(application){

    var myAdapter: DogAdapter? = DogAdapter(application)

    val myDisposable = CompositeDisposable()

    private val myRetrofit: NetworkProvider by lazy{
        NetworkProvider
    }

    fun getPicturesHelper() = myRetrofit.getPictures()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getPictures() =
        myDisposable.add(getPicturesHelper()
            .subscribe({ response ->
                myAdapter?.myList = response
                myAdapter?.notifyDataSetChanged()
            }, { throwable ->
            }))

    override fun onCleared() {
        super.onCleared()
        myDisposable.clear()
        myDisposable.dispose()
        myAdapter = null
    }
}