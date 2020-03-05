package com.illicitintelligence.android.databindingrv.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.illicitintelligence.android.databindingrv.model.PicSumModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class PicViewModelTest {

    @get: Rule
    val instantTaskExecutor = InstantTaskExecutorRule()
    @InjectMocks
    lateinit var viewModel: PicViewModel
    @Mock
    lateinit var application: Application

    @Before
    @Throws(Exception::class)
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate: Scheduler = object : Scheduler() {
            override fun scheduleDirect(
                run: Runnable,
                delay: Long,
                unit: TimeUnit
            ): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorWorker(Executor { obj: Runnable -> obj.run() }, false)
            }
        }
        //RxJavaPlugins.setInitIoSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        //RxJavaPlugins.setInitComputationSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        //RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        //RxJavaPlugins.setInitSingleSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> immediate }
    }

    @Test
    fun getPicturesHelper() {
        val returnResult = viewModel.getPicturesHelper().blockingFirst()
        assertNotNull(returnResult)
        assertNotEquals(0, returnResult.size)
    }

    @Test
    fun getPictures(){
        val mockViewModel = Mockito.mock(PicViewModel::class.java)
        val returnResult = listOf(PicSumModel("a","b","c",1,2))
        Mockito.`when`(mockViewModel.getPicturesHelper()).thenReturn(Observable.just(returnResult))
        mockViewModel.getPictures()
        assertNotEquals(0,mockViewModel.myAdapter?.myList?.size)
    }
}