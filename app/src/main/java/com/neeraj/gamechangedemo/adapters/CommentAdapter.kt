package com.neeraj.gamechangedemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.neeraj.gamechangedemo.R
import com.neeraj.gamechangedemo.databinding.CommentItemBinding
import com.neeraj.gamechangedemo.model.Comment
import com.neeraj.gamechangedemo.viewModel.CommentVM

class CommentAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var commentList=ArrayList<Comment>()

    fun updateItemList(commentList:ArrayList<Comment>){
        this.commentList.clear()
        this.commentList.addAll(commentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.comment_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder) holder.bind(commentList[position])
    }

    override fun getItemCount() = commentList.size

    override fun getItemId(position: Int) = position.toLong()

    internal inner class MyViewHolder(private val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment){
            val viewModel= CommentVM(comment)
            binding.viewModel=viewModel
        }
    }
}