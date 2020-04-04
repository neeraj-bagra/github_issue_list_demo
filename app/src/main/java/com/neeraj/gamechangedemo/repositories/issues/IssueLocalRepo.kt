package com.neeraj.gamechangedemo.repositories.issues

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.utils.AppSharedPref
import com.neeraj.gamechangedemo.utils.Constants

class IssueLocalRepo(var mContext:Context) {
    private val issueListLiveData= MutableLiveData<ArrayList<Issue>>()

    @SuppressLint("StaticFieldLeak")
    fun getIssueList():LiveData<ArrayList<Issue>>{
        object :AsyncTask<String,String,String>(){
            override fun doInBackground(vararg params: String?): String {
                val list=AppSharedPref(mContext).getArrayList(Constants.ISSUE_LIST_PREF,Array<Issue>::class.java)
                issueListLiveData.postValue(list)
                return ""
            }
        }.execute()
        return issueListLiveData
    }
}