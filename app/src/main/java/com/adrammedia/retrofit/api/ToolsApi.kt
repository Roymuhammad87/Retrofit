package com.adrammedia.retrofit.api

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ToolsApi {
    @GET("abc")
    suspend fun getTools():ResponseBody
}