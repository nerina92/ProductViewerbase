package com.cursosant.productviewerbase

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun setNewQuantity_sum_increasesTextField(){
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
        onView(withId(R.id.ibSum))
            .perform(click())
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("2")))
    }
    @Test
    fun setNewQuantity_sumLimit_noIncreasesTextField(){
        val scenario = activityRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity ->
            activity.selectedProduct.quantity=1
        }
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
        onView(withId(R.id.ibSum))
            .perform(click())
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
    }

    @Test
    fun setNewQuantity_sub_decreasesTextField(){
        onView(withId(R.id.etNewQuantity))
            .perform(ViewActions.replaceText("11"))
        onView(withId(R.id.ibSub))
            .perform(click())
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("10")))

    }
    @Test
    fun setNewQuantity_subLimit_noDecreasesTextField(){
        val scenario = activityRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.etNewQuantity))//este id se busca entre todos los archivos de vista del proyecto , hay problemas si se repiten dos id en diferentes vistas.
            .check(matches(withText("1")))
        onView(withId(R.id.ibSub))
            .perform(click())
        onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))
    }

    @Test
    fun checkTextField_startQuantity(){
        /*onView(withId(R.id.etNewQuantity))
            .check(matches(withText("1")))*///De esta forma da error.
        onView(allOf(withId(R.id.etNewQuantity), withContentDescription("cantidad" )))
            .check(matches(withText("1")))
        onView(allOf(withId(R.id.etNewQuantity), not(withContentDescription("cantidad alterna" ))))//de  todas, la que no coincida con esta descripcion, seria lo mismo que la de arriba
            .check(matches(withText("1")))
        onView(allOf(withId(R.id.etNewQuantity), withContentDescription("cantidad alterna" )))
            .check(matches(withText("5")))
    }
}