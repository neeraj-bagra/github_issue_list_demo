package com.neeraj.gamechangedemo.utils.extensions

import java.util.*

fun <T> catchException(func: () -> T?,onExceptionFunction: () -> T?):T? {
    return try {
        func()
    }catch (e: java.lang.Exception){
        e.printStackTrace()
        onExceptionFunction()
    }
}

fun <T> catchException(func: () -> T?):T? {
    return try {
        func()
    }catch (e: java.lang.Exception){
        e.printStackTrace()
        null
    }
}

fun <T> runTaskAfterTime(afterMillisecond:Int=0,afterSec:Int=0, afterMin:Int=0, func: () -> T?) {
    Timer().schedule(object : TimerTask() {
        override fun run() {
            try {
                func()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }, 0L.addMilliSec(afterMillisecond).addSec(afterSec).addMin(afterMin))
}