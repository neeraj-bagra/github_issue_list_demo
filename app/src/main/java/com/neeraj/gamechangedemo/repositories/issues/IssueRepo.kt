package com.neeraj.gamechangedemo.repositories.issues

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.utils.AppSharedPref
import com.neeraj.gamechangedemo.utils.Constants
import com.neeraj.gamechangedemo.utils.extensions.isExpiredNow

abstract class IssueRepo(var mContext: Context) {

    fun getIssueList(): LiveData<ArrayList<Issue>> {
        return if(AppSharedPref(mContext).getLong(Constants.LAST_ISSUE_API_SYNC_LONG_PREF).isExpiredNow(expiryHours = 24)){
            //cache expired i.e. more than 24 hours. so call api
            object :IssueRemoteRepo(mContext){
                override fun onInternetError() {
                    this@IssueRepo.onInternetError()
                }
            }.getIssueList()
        }else{
            //cache is not older than 24 Hrs. so show issue from cache
            IssueLocalRepo(mContext).getIssueList()
        }
    }

    abstract fun onInternetError()
}