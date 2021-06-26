package com.the.classifiedsapp.networkutility.interfaces

interface IServiceResponse {

    fun onServiceResponse(response: String?, serviceTag: String?)
    fun onServiceError(serviceTag: String?)
}