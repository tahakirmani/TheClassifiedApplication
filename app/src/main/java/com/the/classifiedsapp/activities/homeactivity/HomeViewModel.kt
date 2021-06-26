package com.the.classifiedsapp.activities.homeactivity

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedDTO
import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedFeedModel
import com.the.classifiedsapp.networkutility.NetworkUtility
import com.the.classifiedsapp.networkutility.interfaces.IServiceResponse
import com.the.classifiedsapp.networkutility.interfaces.RequestMethods
import com.the.classifiedsapp.utils.AppDateUtils
import com.the.classifiedsapp.utils.CacheManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application), IServiceResponse{

    private var TAG = HomeViewModel::class.java.simpleName
    private var cacheManager: CacheManager? = CacheManager(getApplication())
    private var DEFAULF_CACHE_TIME = 3000000 //Caching feeds for 50 minutes.
    private var serviceURL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/dynamodb-writer"


    /************
     *
     * Observables
     *
     ******/
    private var observableClassifiedFeed : MutableLiveData<ClassifiedFeedModel> =
        MutableLiveData<ClassifiedFeedModel>()


    /***********************
     *
     *
     * Shared Preference Variables
     *
     ******/

    private val PREF_CLASSIFIED_FEED = "PREF_CLASSIFIED_FEED"


    /*********************
     *
     * Service Callbacks
     *
     *******/

    private val CALLBACK_CLASSIFIED_FEED = "CALLBACK_CLASSIFIED_FEED"




    /**********************
     *
     *      OBSERVERS
     *
     ************/

    fun observeClassifiedFeed(): MutableLiveData<ClassifiedFeedModel> {
        return observableClassifiedFeed
    }


    fun callClassifiedFeed(isRefreshRequired : Boolean){

        if(!isRefreshRequired){

            if(cacheManager?.isObjectExists(PREF_CLASSIFIED_FEED, DEFAULF_CACHE_TIME) == true){
                parseClassifiedFeedResponse(cacheManager?.getJSONObject(PREF_CLASSIFIED_FEED))
                return
            }
        }

        val networkUtility = NetworkUtility(this, CALLBACK_CLASSIFIED_FEED)
        networkUtility.callService(serviceURL, null, RequestMethods.GET)
    }


    private fun parseClassifiedFeedResponse(response: String?) {

        CoroutineScope(Dispatchers.Default).launch {

            val gsonBuilder = GsonBuilder().serializeNulls()
            val gson = gsonBuilder.create()
            var classifiedDTO: ClassifiedDTO? = null

            classifiedDTO = gson.fromJson(response, ClassifiedDTO::class.java)

            for (i in 0 until classifiedDTO.results.size) {
                val formattedDate =
                    AppDateUtils.parseClassifiedDate(classifiedDTO.results.get(i).createdAt)
                classifiedDTO.results.get(i).parsedDate = formattedDate
            }


            withContext(Dispatchers.Main) {
                val classifiedFeedModel = ClassifiedFeedModel()
                classifiedFeedModel.isSuccess = true
                classifiedFeedModel.classifiedDTO = classifiedDTO
                observableClassifiedFeed.value = classifiedFeedModel
            }
        }
    }

    override fun onServiceResponse(response: String?, serviceTag: String?) {

        if(serviceTag?.equals(CALLBACK_CLASSIFIED_FEED) == true){
            cacheManager?.saveObjectToPreference(PREF_CLASSIFIED_FEED, response, System.currentTimeMillis())
            parseClassifiedFeedResponse(response)
        }

    }

    override fun onServiceError(serviceTag: String?) {

        if(serviceTag.equals(CALLBACK_CLASSIFIED_FEED)){
            createErrorObjectForClassifiedFeed()
        }

    }

    private fun createErrorObjectForClassifiedFeed() {
        val classifiedFeedModel = ClassifiedFeedModel()
        classifiedFeedModel.isSuccess = false
        observableClassifiedFeed.value = classifiedFeedModel
    }

}