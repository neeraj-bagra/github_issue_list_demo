package com.neeraj.gamechangedemo.repositories.comments

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neeraj.gamechangedemo.model.Comment
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.network.NetworkService
import com.neeraj.gamechangedemo.utils.AppSharedPref
import com.neeraj.gamechangedemo.utils.Constants
import com.neeraj.gamechangedemo.utils.extensions.isExpiredNow
import com.neeraj.gamechangedemo.utils.extensions.nowMillis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class CommentRepo(var issueID:String) {

    private val commentListLiveData= MutableLiveData<ArrayList<Comment>>()

    init {
        callCommentAPI()
    }

    fun getIssueList() = commentListLiveData

    private fun callCommentAPI(){
        val sub= NetworkService.getInstance().getApi().getComments(issueID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { result -> onSuccess(result) },
                { t: Throwable? ->  t?.printStackTrace(); onInternetError() }
            )
    }

    private fun onSuccess(result:ArrayList<Comment>){
        Log.e("issue",result.size.toString())
        commentListLiveData.value = result
    }

    abstract fun onInternetError()
}