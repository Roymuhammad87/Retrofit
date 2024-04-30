package com.adrammedia.retrofit.api

import com.adrammedia.retrofit.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class PostApiBuilder {
//    val postApi: PostApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(PostApi::class.java)
//    }
      var postApi: PostApi = Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(PostApi::class.java)

     companion object {
         private var INSTANCE:PostApiBuilder? = null
         fun getInstance():PostApiBuilder {
             return INSTANCE?: PostApiBuilder()
         }
     }
}