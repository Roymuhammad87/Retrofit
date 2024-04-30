package com.adrammedia.retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrammedia.retrofit.adapters.PostAdapter
import com.adrammedia.retrofit.data.postsmodel.PostItem
import com.adrammedia.retrofit.databinding.ActivityMainBinding
import com.adrammedia.retrofit.utils.Constants
import com.adrammedia.retrofit.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postAdapter = PostAdapter()
        setUpPostsRv()
        postViewModel.getAllPosts()
        populatePosts()
        postAdapter.onPostClick = {
            val postIntent = Intent(this, ViewPostCommentsActivity::class.java)
            postIntent.putExtra(Constants.POST_INTENT_KEY, it.id)
            postIntent.putExtra(Constants.POST_INTENT_TITLE_KEY, it.title)
            startActivity(postIntent)
        }
        binding.floatingActionButton.setOnClickListener {
            val postItem = PostItem(body = "here is my body", title = "my title", userId = 550)
            postViewModel.insertPost(postItem)
            postViewModel.insertPostMutableLiveDat.observe(this, Observer {
//                val intent = Intent(this@MainActivity, AddPostActivity::class.java)
//                intent.putExtra("AddedPost", it.title)
//                startActivity(intent)
                Toast.makeText(this@MainActivity, "${it.title}", Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun populatePosts() {
        postViewModel.postMutableLiveData.observe(this) { value ->
            postAdapter.differ.submitList(value)
        }
    }

    private fun setUpPostsRv() {
        binding.rvMainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}