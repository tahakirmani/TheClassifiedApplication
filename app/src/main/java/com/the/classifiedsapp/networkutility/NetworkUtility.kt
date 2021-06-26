package com.the.classifiedsapp.networkutility

import android.util.Log
import com.google.gson.JsonObject
import com.the.classifiedsapp.networkutility.interfaces.IServiceResponse
import com.the.classifiedsapp.networkutility.interfaces.RequestMethods
import com.the.classifiedsapp.networkutility.retrofit.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkUtility(private var iServiceResponse: IServiceResponse?, private var serviceTag: String) {

    private val TAG = NetworkUtility::class.java.simpleName
    private var URL: String? = null
    private var jsonObject: JsonObject? = null
    private val RESPONSE_SUCCESS = 200
    private var call: Call<JsonObject>? = null
    private var action: Int?= null


    fun callService(URL: String?, jsonObject: JsonObject?, action: Int) {
        this.URL = URL
        this.action = action
        this.jsonObject = jsonObject


        printLogMessages(URL, jsonObject, action)


        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API.BASE_URL)
            .build()
        val api: API = retrofit.create(API::class.java)


        if (action == RequestMethods.GET) {
            call = api.networkUtilityGetCall()
        }


        call?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.code() == RESPONSE_SUCCESS) {
                    parseResponse(response)
                    Log.d("NU_RESPONSE", response.body().toString())
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                iServiceResponse?.onServiceError(serviceTag)
                Log.d(TAG, "Failure_$serviceTag")
            }
        })

    }

    private fun printLogMessages(URL: String?, jsonObject: JsonObject?, action: Int) {
        if (action == RequestMethods.GET) {
            Log.d(TAG, "GET")
        } else if (action == RequestMethods.POST) {
            Log.d(TAG, "POST")
        }
        if (URL != null) {
            Log.d(TAG, URL)
        }
        if (jsonObject != null) {
            Log.d(TAG, jsonObject.toString())
        }
    }


    private fun parseResponse(response: Response<JsonObject>) {

        val jsonObject = response.body()
        iServiceResponse?.onServiceResponse(jsonObject.toString(), serviceTag)
    }

}
