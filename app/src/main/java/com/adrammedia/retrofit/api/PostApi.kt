package com.adrammedia.retrofit.api

import com.adrammedia.retrofit.data.commentsmodel.CommentsItem
import com.adrammedia.retrofit.data.photos.PhotosResponse
import com.adrammedia.retrofit.data.postsmodel.Post
import com.adrammedia.retrofit.data.postsmodel.PostItem
import com.adrammedia.retrofit.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Objects

interface PostApi {
    //GET POSTS
    @GET(Constants.POST_END_PONT)
    fun getPosts():Call<Post>
    /**
     * =============================================================================
     */

    //GET A POST COMMENTS
    @GET(Constants.COMMENT_END_PONT)
    fun getPostComments(@Query("postId") postId:Int):Call<List<CommentsItem>>

    /**
     * ====================================================================
     */
    //INSERT A POST IN DATABASE
    @POST(Constants.POST_END_PONT)
    fun insertPost(@Body post: PostItem):Call<PostItem>

    /**
     * ====================================================================
     */
    //INSERT A MAP IN DATABASE
    @POST(Constants.POST_END_PONT)
    fun insertMap(@Body map: HashMap<Object,Object>):Call<PostItem>

    @GET(Constants.PHOTOS_END_PONT)
    suspend fun getPhotos():Flow<Response<PhotosResponse>>
}