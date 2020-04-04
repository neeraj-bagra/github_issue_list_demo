package com.neeraj.gamechangedemo.utils.extensions

fun <T> List<T>.getOrElse(index: Int, defaultValue: T): T {
    return if (index in 0..lastIndex) get(index) else defaultValue
}

fun <T,R> List<T>.ifNotEmpty(someTask: (list:List<T>) -> R): List<T> {
    if (isNotEmpty()) someTask(this)
    return this
}
