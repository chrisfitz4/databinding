package com.illicitintelligence.android.databindingrv

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.illicitintelligence.android.databindingrv.view.DogAdapter
import com.illicitintelligence.android.databindingrv.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DoesTheViewModelGetANything {


    @Test
    fun testActivity() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(5000)
        onView(withId(R.id.picSumRV))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<DogAdapter.MyViewHolder>(3, click())
            )
        onView(withId(R.id.picSumRV))
            .check(matches(isDisplayed()))
    }

}