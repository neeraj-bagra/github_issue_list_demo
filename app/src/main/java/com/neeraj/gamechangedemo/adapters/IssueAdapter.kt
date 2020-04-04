package com.neeraj.gamechangedemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neeraj.gamechangedemo.R
import com.neeraj.gamechangedemo.databinding.IssueItemBinding
import com.neeraj.gamechangedemo.model.Issue
import com.neeraj.gamechangedemo.viewModel.IssueVM

class IssueAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var issueList=ArrayList<Issue>()

    fun updateItemList(issueList:ArrayList<Issue>){
        this.issueList.clear()
        this.issueList.addAll(issueList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.issue_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder) holder.bind(issueList[position])
    }

    override fun getItemCount() = issueList.size

    override fun getItemId(position: Int) = position.toLong()

    internal inner class MyViewHolder(private val binding: IssueItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(issueItem: Issue?){
            issueItem?.let{
                val viewModel= IssueVM(it)
                binding.viewModel=viewModel
            }
        }
    }
}