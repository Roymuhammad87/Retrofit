package com.adrammedia.retrofit.data.postsmodel

import java.io.Serializable

data class PostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
):Serializable {
    constructor(body: String, title: String, userId: Int): this("", 0, "", 0)
}