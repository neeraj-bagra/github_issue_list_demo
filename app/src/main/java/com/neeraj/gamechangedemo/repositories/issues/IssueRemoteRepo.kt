package com.neeraj.gamechangedemo.repositories.issues

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.network.NetworkService
import com.neeraj.gamechangedemo.utils.AppSharedPref
import com.neeraj.gamechangedemo.utils.Constants
import com.neeraj.gamechangedemo.utils.extensions.nowMillis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class IssueRemoteRepo(var mContext: Context) {
    private val issueLiveData=MutableLiveData<ArrayList<Issue>>()

    init {
        callNextPageAPI()
    }

    fun getIssueList() = issueLiveData

    private fun callNextPageAPI(page:Int=1){
        val sub=NetworkService.getInstance().getApi().getIssues(page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
            { result -> onSuccess(result,page) },
            { t: Throwable? ->  t?.printStackTrace(); onInternetError() }
        )
    }

    private fun onSuccess(result:ArrayList<Issue>,page: Int){
        if(issueLiveData.value==null){
            issueLiveData.value = result
        }else {
            issueLiveData.value = issueLiveData.value.apply { this?.addAll(result) }
        }
        if(result.size>=100){
            //have more page
            callNextPageAPI(page+1)
        }else{
            AppSharedPref(mContext).save(Constants.LAST_ISSUE_API_SYNC_LONG_PREF, nowMillis())
            AppSharedPref(mContext).saveObject(Constants.ISSUE_LIST_PREF,issueLiveData.value!!)
        }
    }

    abstract fun onInternetError()
}