package com.neeraj.gamechangedemo.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.neeraj.gamechangedemo.CommentListActivity
import com.neeraj.gamechangedemo.R
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.utils.DialogUtils

class IssueVM(var issue:Issue) {

    fun showComments(view: View){
        if(issue.comments==0){
            DialogUtils.showNoticeDialog(view.context, R.string.ok,R.string.no_comments_available,null)
        }else{
            view.context.startActivity(CommentListActivity.getIntent(view.context,issue.number?:""))
        }
    }
}