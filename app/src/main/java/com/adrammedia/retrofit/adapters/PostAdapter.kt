package com.adrammedia.retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.adrammedia.retrofit.data.postsmodel.PostItem
import com.adrammedia.retrofit.databinding.PostItemBinding

class PostAdapter:RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private val diffUtil = object :DiffUtil.ItemCallback<PostItem>(){
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, diffUtil)
     var onPostClick:((PostItem) ->Unit)? = null
    inner class PostViewHolder(private val binding: PostItemBinding):ViewHolder(binding.root){
        fun bind(postItem: PostItem){
            binding.apply {
                tvItemPostTitle.text = postItem.title
                tvItemPostBody.text = postItem.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(PostItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postItem = differ.currentList[position]
        holder.bind(postItem)
        holder.itemView.setOnClickListener {
            onPostClick!!.invoke(postItem)
        }
    }
}