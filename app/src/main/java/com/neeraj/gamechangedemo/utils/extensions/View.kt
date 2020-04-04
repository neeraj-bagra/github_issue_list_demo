package com.neeraj.gamechangedemo.utils.extensions

import android.content.ContextWrapper
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

fun TextView.markRequired(isCompulsory:Boolean) {
    if(isCompulsory){
        if(hint!=null&&hint.isNotBlank()){
            if(hint!!.contains("*").not()) {
                hint = "$hint *"
            }
        }
        else if(text!=null&&text.isNotBlank()) {
            if(text!!.contains("*").not()){
                text = text.toString().concat(" *")
            }
        }
    }
}

fun View.markVisibleIF(isVisible:Boolean) {
    this.visibility=if(isVisible) View.VISIBLE else View.GONE
}

fun View.markVisibleOrInvisibleIf(visibilityCondition:Boolean) {
    this.visibility=if(visibilityCondition) View.VISIBLE else View.INVISIBLE
}

fun View.setBackgroundHexColor(hexColor:String) {
    catchException { setBackgroundColor(Color.parseColor(hexColor)) }
}

fun TextView.setTextHexColor(hexColor:String) {
    catchException { setTextColor(Color.parseColor(hexColor)) }
}
fun View.toggleVisibility() {
    this.visibility=if(isVisible) View.GONE else View.VISIBLE
}

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}