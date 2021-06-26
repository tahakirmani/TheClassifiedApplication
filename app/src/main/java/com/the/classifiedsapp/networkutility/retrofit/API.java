package com.the.classifiedsapp.networkutility.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface API {

    String BASE_URL = "https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/default/dynamodb-writer/";

    @GET(".")
    Call<JsonObject> networkUtilityGetCall();


}
