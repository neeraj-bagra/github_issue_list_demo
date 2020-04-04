package com.neeraj.gamechangedemo.viewModel

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neeraj.gamechangedemo.R
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.repositories.issues.IssueRepo

public class IssueListVM(var app: Application): AndroidViewModel(app) {
    var progressBarVisibility=MutableLiveData<Int>()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadIssueList() }
    lateinit var issueList: LiveData<ArrayList<Issue>>

    init {
        loadIssueList()
    }

    private fun loadIssueList(){
        progressBarVisibility.value=View.VISIBLE
        issueList=object :IssueRepo(app){
            override fun onInternetError() {
                this@IssueListVM.onInternetError()
            }
        }.getIssueList()
    }

    fun onInternetError(){
        progressBarVisibility.value=View.GONE
        errorMessage.value = R.string.issue_list_error
    }

}