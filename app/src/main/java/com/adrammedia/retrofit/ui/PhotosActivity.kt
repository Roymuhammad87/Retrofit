package com.adrammedia.retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adrammedia.retrofit.R
import com.adrammedia.retrofit.databinding.ActivityPhotosBinding
import com.adrammedia.retrofit.viewmodel.PostViewModel
import kotlinx.coroutines.launch

class PhotosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotosBinding
    private lateinit var postViewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        getPhotos()
    }

    private fun getPhotos() {
       postViewModel.getPhotos()
        lifecycleScope.launch {
            postViewModel.photosMutableLiveData.observe(this@PhotosActivity){
                for (i in it.indices){
                    Log.d("TAG", "getPhotos: ${it[i].title}")
                }
            }
        }
    }


}