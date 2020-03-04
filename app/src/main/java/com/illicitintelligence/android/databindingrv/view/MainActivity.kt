package com.illicitintelligence.android.databindingrv.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.illicitintelligence.android.databindingrv.R
import com.illicitintelligence.android.databindingrv.databinding.ActivityMainBinding
import com.illicitintelligence.android.databindingrv.viewmodel.PicViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myViewModel: PicViewModel
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myViewModel = ViewModelProvider(this).get(PicViewModel::class.java)
        Log.d("TAG_X","onCreate: ${myViewModel.myAdapter==null}")

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding?.picViewModel=myViewModel
        //setContentView(R.layout.activity_main)

        (binding?.picSumRV?.layoutManager as GridLayoutManager?)?.apply {
            spanCount = 3
        }
        myViewModel.getPictures()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding?.unbind()
        binding=null
    }
}
