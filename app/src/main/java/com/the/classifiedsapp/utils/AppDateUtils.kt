package com.the.classifiedsapp.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object AppDateUtils {

    //2019-02-23 07:56:26.686128

    @SuppressLint("SimpleDateFormat")
    fun parseClassifiedDate(rawDate : String) : String{

        var parsedDate: Date? = null
        var formattedDate = ""


        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val UIDateFormat = SimpleDateFormat("dd MMM yyyy")

        try {
            parsedDate = dateFormat.parse(rawDate)
            formattedDate = UIDateFormat.format(parsedDate)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return formattedDate
    }

}