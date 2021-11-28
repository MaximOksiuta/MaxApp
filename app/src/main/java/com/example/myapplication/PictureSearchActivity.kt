package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide

class PictureSearchActivity : AppCompatActivity() {
    private lateinit var etSearchRequest: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_search)
        etSearchRequest = findViewById(R.id.et_SearchRequest)
        searchButton = findViewById(R.id.btnSearch)
        imageView = findViewById(R.id.iv_searchedImage)
        searchButton.setOnClickListener(View.OnClickListener {
            var url = "https://source.unsplash.com/random?"
            url += etSearchRequest.text
            Glide
                .with(this)
                .load(url)
                .placeholder(R.drawable.ic_baseline_cloud_download_24)
                .into(imageView);
        })
    }
}