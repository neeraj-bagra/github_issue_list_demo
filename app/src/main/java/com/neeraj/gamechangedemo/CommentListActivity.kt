package com.neeraj.gamechangedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.neeraj.gamechangedemo.adapters.CommentAdapter
import com.neeraj.gamechangedemo.databinding.ActivityCommentListBinding
import com.neeraj.gamechangedemo.viewModel.CommentListVM

class CommentListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentListBinding
    val viewModel: CommentListVM by viewModels()
    private var errorSnackBar: Snackbar? = null
    private var adapter= CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comment_list)
        setTitleBar()
        binding.commentsRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.commentsRV.adapter=adapter
        viewModel.loadCommentList(intent?.extras?.getString(ISSUE_ID_ARG)?:"")

        viewModel.commentList.observe(
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

    private fun setTitleBar() {
        supportActionBar?.setTitle(R.string.comments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackBar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackBar?.show()
    }

    private fun hideError(){
        errorSnackBar?.dismiss()
    }

    companion object{
        const val ISSUE_ID_ARG = "ISSUE_ID_ARG"
        fun getIntent(mContext: Context,issueID:String) = Intent(mContext,CommentListActivity::class.java).apply { putExtra(ISSUE_ID_ARG,issueID) }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
