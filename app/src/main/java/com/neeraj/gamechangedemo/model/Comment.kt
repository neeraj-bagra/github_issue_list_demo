package com.neeraj.gamechangedemo.model

import com.google.gson.annotations.SerializedName

class Comment {
    @SerializedName("user")
    private var user:HashMap<String,String>?=null

    @SerializedName("body")
    var body:String?=null
        get() = field?:""

    fun getUserName() = user?.get("login")?:"NA"
}