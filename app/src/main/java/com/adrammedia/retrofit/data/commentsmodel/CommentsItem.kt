package com.adrammedia.retrofit.data.commentsmodel

data class CommentsItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)