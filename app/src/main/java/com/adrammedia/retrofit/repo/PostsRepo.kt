package com.adrammedia.retrofit.repo

import com.adrammedia.retrofit.api.PostApiBuilder
import com.adrammedia.retrofit.data.commentsmodel.CommentsItem
import com.adrammedia.retrofit.data.postsmodel.Post
import com.adrammedia.retrofit.data.postsmodel.PostItem
import retrofit2.Call

class PostsRepo {
    val postApiBuilder = PostApiBuilder.getInstance()
   fun getAllPosts():Call<Post>{
       return postApiBuilder.postApi.getPosts()
   }
    fun getPostComments(postId:Int):Call<List<CommentsItem>>{
        return postApiBuilder.postApi.getPostComments(postId)
    }
    fun insertPost(postItem: PostItem):Call<PostItem>{
        return postApiBuilder.postApi.insertPost(post = postItem)
    }
}