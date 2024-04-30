package com.adrammedia.retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.adrammedia.retrofit.data.commentsmodel.CommentsItem
import com.adrammedia.retrofit.data.postsmodel.Post
import com.adrammedia.retrofit.data.postsmodel.PostItem
import com.adrammedia.retrofit.repo.PostsRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel:ViewModel() {
    private val postsRepo = PostsRepo()

    //POSTS
    private val _postMutableLiveData = MutableLiveData<Post>()
    val postMutableLiveData:LiveData<Post> = _postMutableLiveData
    fun getAllPosts(){
       postsRepo.getAllPosts().enqueue(object :Callback<Post>{
           override fun onResponse(call: Call<Post>, response: Response<Post>) {
               if (response.body() != null){
                   _postMutableLiveData.postValue(response.body())
               }
           }

           override fun onFailure(call: Call<Post>, t: Throwable) {
               Log.d("TAG", "onFailure: ${t.message}")
           }
       })
    }
    /***
     * =============================================================
     */
    //COMMENTS
    private val _commentMutableLiveData = MutableLiveData<List<CommentsItem>>()
    val commenttMutableLiveData:LiveData<List<CommentsItem>> = _commentMutableLiveData
    fun getPostComments(postId:Int){
        postsRepo.getPostComments(postId).enqueue(object :Callback<List<CommentsItem>>{
            override fun onResponse(
                call: Call<List<CommentsItem>>,
                response: Response<List<CommentsItem>>
            ) {
                if (response.body() != null){
                    _commentMutableLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<CommentsItem>>, t: Throwable) {
                Log.d("TAG", "onFailure: ${t.message}")
            }
        })
    }
    /**
     * ==================================================================
     */
    private val _insertPostMutableLiveDat = MutableLiveData<PostItem>()
    val insertPostMutableLiveDat = _insertPostMutableLiveDat
    fun insertPost(postItem: PostItem){
        postsRepo.insertPost(postItem).enqueue(object :Callback<PostItem>{
            override fun onResponse(call: Call<PostItem>, response: Response<PostItem>) {
                    _insertPostMutableLiveDat.value = response.body()!!
                    Log.d("TAG", "sonuc: insert post success ${response.body()!!.title}")

            }

            override fun onFailure(call: Call<PostItem>, t: Throwable) {
                Log.d("TAG", "sonuc: insert post failed ${t.message}")

            }

        })
    }
}