package com.the.classifiedsapp.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class CacheManager {

    private Context context;
    private String jsonString;
    private SharedPreferences sharedPreferences;
    private long savedTime;

    final String JSON_OBJECT= "JSON_OBJECT";
    final  String TIME_STAMP= "TIME_STAMP";

    public CacheManager(Context context){
        this.context= context.getApplicationContext();
    }


    public  void saveObjectToPreference(String preferenceReference, String jsonObject, long dateTime){
        SharedPreferences sharedPreferences= context.getSharedPreferences(preferenceReference,0);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putLong(TIME_STAMP, dateTime);
        editor.putString(JSON_OBJECT, jsonObject);
        editor.apply();
    }


    public boolean isObjectExists(String preferenceReference, int expiryDuration) {

        boolean isJSONObjectExists= false;

        sharedPreferences = context.getSharedPreferences(preferenceReference, Context.MODE_PRIVATE);
        jsonString= sharedPreferences.getString(JSON_OBJECT, "");


        if (sharedPreferences.contains(TIME_STAMP)) {
            savedTime = sharedPreferences.getLong(TIME_STAMP, -1);
            if(Math.abs(System.currentTimeMillis()- savedTime) < expiryDuration){

                if(!jsonString.isEmpty()) {
                    isJSONObjectExists = true;
                }
            }
        }
        return isJSONObjectExists;
    }

    public String getJSONObject(String preferenceReference){

        String cacheObject;

        SharedPreferences sharedPreferences= context.getSharedPreferences(preferenceReference, Context.MODE_PRIVATE);
        jsonString= sharedPreferences.getString(JSON_OBJECT, "");

        if(!jsonString.isEmpty()) {
            cacheObject=  jsonString;
        }else{
            cacheObject= "";
        }

        return cacheObject;
    }
}