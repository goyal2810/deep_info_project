package com.example.deepinfo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    String REMEMBER_KEY = "isRemembered";


    public SharedManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(PersonInfo personInfo, Boolean isChecked){
        String userId = personInfo.getUserId();
        editor.putString(SESSION_KEY, userId).commit();
        editor.putBoolean(REMEMBER_KEY, isChecked).commit();
    }

    public String getSession(){
        return sharedPreferences.getString(SESSION_KEY, null);
    }

    public Boolean isRemebered(){
        return sharedPreferences.getBoolean(REMEMBER_KEY, false);
    }

    public void removeSession(){
        editor.putString(SESSION_KEY, null).commit();
    }
}
