package com.the.classifiedsapp.activities.homeactivity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.the.classifiedsapp.R
import junit.framework.TestCase
import org.junit.Rule


class HomeActivityTest : TestCase() {

    @Rule
    val mActivityRule: ActivityTestRule<HomeActivity> = ActivityTestRule(HomeActivity::class.java)


    fun test_isActivityInView(){
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        onView(withId(R.id.homeParentLayout)).check(matches(isDisplayed()))
    }


    private fun getCount(): Int? {
        val recyclerView =
            mActivityRule.getActivity().findViewById(R.id.rvHomeList) as RecyclerView
        return recyclerView.adapter?.itemCount
    }

    fun testOnCreate() {}

    fun testOnClassifiedTapped() {}

    fun testOnClick() {}
}