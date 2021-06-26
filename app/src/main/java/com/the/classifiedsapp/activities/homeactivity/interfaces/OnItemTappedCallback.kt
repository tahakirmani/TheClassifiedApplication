package com.the.classifiedsapp.activities.homeactivity.interfaces

import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedData

interface OnItemTappedCallback {

    fun onClassifiedTapped(classifiedData: ClassifiedData?)

}