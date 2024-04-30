package com.adrammedia.retrofit.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.adrammedia.retrofit.data.commentsmodel.CommentsItem
import com.adrammedia.retrofit.databinding.CommentItemBinding

class CommentsAdapter:RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    val diffUtil = object : DiffUtil.ItemCallback<CommentsItem>(){
        override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffUtil)
    class CommentsViewHolder(private val binding: CommentItemBinding):ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(commentsItem: CommentsItem){
            binding.tvItemCommentTv.text = commentsItem.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(CommentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = differ.currentList[position]
        holder.bind(comment)
    }
}