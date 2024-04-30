package com.adrammedia.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adrammedia.retrofit.R
import com.adrammedia.retrofit.data.postsmodel.PostItem
import com.adrammedia.retrofit.databinding.ActivityAddPostActivtyBinding

class AddPostActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAddPostActivtyBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val intent = intent
            val title = intent.getStringExtra("AddedPost")
            binding.textView.text  = title!!
        Toast.makeText(this, "$title", Toast.LENGTH_SHORT).show()

    }
}