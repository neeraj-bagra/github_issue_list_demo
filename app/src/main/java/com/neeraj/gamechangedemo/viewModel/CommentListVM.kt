package com.neeraj.gamechangedemo.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neeraj.gamechangedemo.R
import com.neeraj.gamechangedemo.model.Comment
import com.neeraj.gamechangedemo.repositories.comments.CommentRepo

class CommentListVM(var app: Application): AndroidViewModel(app) {
    var issueID:String=""
    var progressBarVisibility=MutableLiveData<Int>()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCommentList(issueID) }
    lateinit var commentList: LiveData<ArrayList<Comment>>

    fun loadCommentList(issueID:String){
        this.issueID=issueID
        progressBarVisibility.value=View.VISIBLE
        commentList=object :CommentRepo(issueID){
            override fun onInternetError() {
                this@CommentListVM.onInternetError()
            }
        }.getIssueList()
    }

    fun onInternetError(){
        progressBarVisibility.value=View.GONE
        errorMessage.value = R.string.issue_list_error
    }

}