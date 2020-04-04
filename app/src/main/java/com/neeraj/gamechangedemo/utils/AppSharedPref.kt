package com.neeraj.gamechangedemo.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Nullable
import com.google.gson.Gson
import com.neeraj.gamechangedemo.utils.extensions.catchException
import com.neeraj.gamechangedemo.utils.extensions.convertToArrayList


class AppSharedPref(var mContext: Context) {

    private val sharedPreference: SharedPreferences by lazy { mContext.getSharedPreferences(APP_PREF, Activity.MODE_PRIVATE) }
    private val editor by lazy { sharedPreference.edit() }

    fun add(key:String,value:String?):AppSharedPref{
        editor.putString(key, value)
        return this
    }

    fun add(key:String,value:Boolean?):AppSharedPref{
        if(value==null){
            editor.remove(key)
        }else {
            editor.putBoolean(key, value)
        }
        return this
    }

    fun add(key:String,value:Long?):AppSharedPref{
        if(value==null){
            editor.remove(key)
        }else {
            editor.putLong(key, value)
        }
        return this
    }

    fun add(key:String,value:Int?):AppSharedPref{
        if(value==null){
            editor.remove(key)
        }else {
            editor.putInt(key, value)
        }
        return this
    }

    fun addObject(key:String,anyObject:Any?):AppSharedPref{
        if(anyObject==null){
            editor.remove(key)
        }else {
            editor.putString(key, Gson().toJson(anyObject))
        }
        return this
    }

    fun save(key:String,value:String?){
        add(key, value)
        save()
    }

    fun save(key:String,value:Boolean?){
        add(key, value)
        save()
    }

    fun save(key:String,value:Long?){
        add(key, value)
        save()
    }

    fun save(key:String,value:Int?){
        add(key, value)
        save()
    }

    fun saveObject(key:String,value:Any?){
        addObject(key, value)
        save()
    }

    fun save(){
        editor.apply()
    }

    fun saveSync(){
        editor.commit()
    }

    fun getString(key:String)=sharedPreference.getString(key,"")?:""
    fun getBoolean(key:String)=sharedPreference.getBoolean(key,false)
    fun getBoolean(key:String,defaultValue:Boolean)=sharedPreference.getBoolean(key,defaultValue)
    fun getLong(key:String)=sharedPreference.getLong(key,0L)
    fun getInt(key:String)=sharedPreference.getInt(key,0)

    @Nullable
    fun <T> getObject(key:String,clazz: Class<T>):T?{
        return catchException { Gson().fromJson(sharedPreference.getString(key,""),clazz) }
    }

    fun <T> getArrayList(key:String, clazz: Class<Array<T>>): ArrayList<T> {
        return getString(key).convertToArrayList(clazz)
    }

    fun <T> getNullableArrayList(key:String, clazz: Class<Array<T>>): ArrayList<T>? {
        return catchException {ArrayList(listOf(*Gson().fromJson(getString(key), clazz)))}
    }
}