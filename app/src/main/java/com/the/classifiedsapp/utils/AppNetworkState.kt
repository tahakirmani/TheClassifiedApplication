package com.the.classifiedsapp.utils

import android.content.Context
import android.net.ConnectivityManager

object AppNetworkState {

    fun checkConnection(context: Context?): Boolean {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo != null) { // connected to the internet
            // Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                return true
            } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                return true
            }
        }
        return false
    }
}