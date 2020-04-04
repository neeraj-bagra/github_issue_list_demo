package com.neeraj.gamechangedemo.model

import com.google.gson.annotations.SerializedName

class Issue {
    @SerializedName("number")
    var number:String?=null
        get() = field?:""
    @SerializedName("title")
    var title:String?=null
        get() = field?:""
    @SerializedName("body")
    var body:String?=null
        get() = field?:""
    @SerializedName("comments")
    var comments:Int?=null
        get() = field?:0
}