package com.illicitintelligence.android.databindingrv.viewmodel

import android.app.Application
import android.util.Log
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

    fun getPictures() =
        myDisposable.add(myRetrofit.getPictures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                myAdapter?.myList = response
                myAdapter?.notifyDataSetChanged()
                Log.d("TAG_X", "getPictures: ${response.size}")
            }, { throwable ->
                Log.d("TAG_X", "getPictures: uhoh, error: ${throwable.message}")
            }))

    override fun onCleared() {
        super.onCleared()
        myDisposable.clear()
        myDisposable.dispose()
        myAdapter = null
    }
}