package com.neeraj.gamechangedemo.network

import com.neeraj.gamechangedemo.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class NetworkService {
    private var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun getApi():Api{
        return mRetrofit.create(Api::class.java)
    }

    companion object{
        private var mInstance: NetworkService? = null
        fun getInstance():NetworkService{
            if(mInstance==null){
                mInstance=NetworkService()
            }
            return mInstance!!
        }
    }
}