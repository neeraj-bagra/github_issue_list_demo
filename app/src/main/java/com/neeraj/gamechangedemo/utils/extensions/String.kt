package com.neeraj.gamechangedemo.utils.extensions

import android.annotation.SuppressLint
import com.google.gson.Gson

fun String.replaceRsSymbol()=this.replace("Rs.","₹").replace("Rs","₹")

fun String.removeStartingComma()=if(this.startsWith(","))this.removeRange(0,1) else this

fun <T> String.convertToArrayList(clazz: Class<Array<T>>): ArrayList<T> {
    return catchException { ArrayList(listOf(*Gson().fromJson(this, clazz))) }
            ?: ArrayList()
}
fun String.addPaddingAtStart(paddingChar:String,minLength:Int)=(minLength-length).let { if(it>0) paddingChar.repeat(it) else "" }+this

fun String?.replaceIfNullOrEmpty(defaultValue: String) = if(this?.isNotEmpty()==true) this else defaultValue
fun String.concat(str:String)=this+str

fun String.capitalizeAllWords()= this.replace("\\s+".toRegex(), " ").split(" ").joinToString(" ") { it.lower()!!.capitalize() }

@SuppressLint("DefaultLocale")
fun String?.lower()=this?.toLowerCase()
