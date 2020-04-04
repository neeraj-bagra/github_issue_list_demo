package com.neeraj.gamechangedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.neeraj.gamechangedemo.databinding.ActivityIssueListBinding
import com.neeraj.gamechangedemo.viewModel.IssueListVM

class IssuesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIssueListBinding
    val viewModel: IssueListVM by viewModels()
    private var errorSnackBar: Snackbar? = null
    private var adapter=IssueAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_issue_list)
        supportActionBar?.setTitle(R.string.issues)
        binding.issuesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.issuesRV.adapter=adapter


        viewModel.issueList.observe(
            this, Observer {
                viewModel.progressBarVisibility.value= View.GONE
                adapter.updateItemList(it)
            }
        )
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }

    private fun hideError(){
        errorSnackBar?.dismiss()
    }
}
