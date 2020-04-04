package com.neeraj.gamechangedemo.utils.extensions

import java.util.*

fun nowMillis() = Calendar.getInstance().timeInMillis
fun Long.addDays(daysToAdd:Int)= this+(daysToAdd*24*60*60*1000)
fun Long.addHours(hoursToAdd:Int)= this+(hoursToAdd*60*60*1000)
fun Long.addMin(minToAdd:Int)= this+(minToAdd*60*1000)
fun Long.addSec(secToAdd:Int)= this+(secToAdd*1000)
fun Long.addMilliSec(milliSecondsToAdd:Int)= this+milliSecondsToAdd
fun Long.subtractNow()= this- Calendar.getInstance().timeInMillis
fun Long.isExpiredNow(expiryDays:Int=0,expiryHours: Int=0,expiryMin: Int=0)= this.addDays(expiryDays).addHours(expiryHours).addMin(expiryMin).subtractNow()<0
fun Long.isNotExpiredNow(expiryDays:Int=0,expiryHours: Int=0,expiryMin: Int=0)= this.addDays(expiryDays).addHours(expiryHours).addMin(expiryMin).subtractNow()>=0