package com.adrammedia.retrofit.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrammedia.retrofit.R
import com.adrammedia.retrofit.adapters.CommentsAdapter
import com.adrammedia.retrofit.databinding.ActivityViewPostCommentsActivtyBinding
import com.adrammedia.retrofit.utils.Constants
import com.adrammedia.retrofit.viewmodel.PostViewModel

class ViewPostCommentsActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityViewPostCommentsActivtyBinding.inflate(layoutInflater)
    }
    private lateinit var postViewModel: PostViewModel
    private lateinit var commentsAdapter: CommentsAdapter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        commentsAdapter = CommentsAdapter()
        setUpCommentsRecyclerView()
        val commentIntent = intent
        val postId = commentIntent.getIntExtra(Constants.POST_INTENT_KEY, -1)
        val postTitle = commentIntent.getStringExtra(Constants.POST_INTENT_TITLE_KEY)
        if (postId != -1){
            binding.apply {
                tvPostCommentsPostId.text= "Post Id: #"+postId.toString()
                tvPostCommentsPostTitle.text = postTitle
            }
            postViewModel.getPostComments(postId)
            populateComments()
        }
    }

    private fun populateComments() {
        postViewModel.commenttMutableLiveData.observe(this, Observer {
            commentsAdapter.differ.submitList(it)
        })
    }

    private fun setUpCommentsRecyclerView() {
        binding.rvPostCommentsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ViewPostCommentsActivity)
            adapter = commentsAdapter
        }
    }
}