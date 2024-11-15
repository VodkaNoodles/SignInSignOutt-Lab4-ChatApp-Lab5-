package com.example.loginsignup.utilities;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private final SharedPreferences sharedPreferences;
    /**
     * Initializes the PreferenceManager with the given context.
     *
     * @param context the context to use for managing preferences
     */
    public PreferenceManager(Context context){
        this.sharedPreferences = context.getSharedPreferences(Constants.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }
    /**
     * Stores a boolean value in the preferences with the given key.
     *
     * @param key the key with which the value will be associated
     * @param value the boolean value to store
     */
    public void putBoolean(String key, Boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public Boolean getBoolean(String key, Boolean value){
        return sharedPreferences.getBoolean(key, false);
    }
    /**
     * Stores a string value in the preferences with the given key.
     *
     * @param key the key with which the value will be associated
     * @param value the string value to store
     */
    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();

    }

    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }
    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
