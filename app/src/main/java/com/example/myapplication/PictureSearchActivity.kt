package com.example.myapplication

import android.app.DownloadManager
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivityPictureSearchBinding

class PictureSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictureSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchInputTextLayout.setEndIconOnClickListener {
            searchImage(binding.etSearch.text.toString())
        }
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                searchImage(binding.etSearch.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun searchImage(request: String){
        var url = "https://source.unsplash.com/random?$request"
        binding.ivSearchedImage.setImageResource(R.drawable.transparent_background)
        binding.tvLoadImage.visibility = View.GONE
        binding.pbLoadImage.visibility = View.VISIBLE
        Glide
            .with(this)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.transparent_background)
            .addListener(object: RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.ivSearchedImage.setImageResource(R.drawable.transparent_background)
                    binding.pbLoadImage.visibility = View.GONE
                    binding.tvLoadImage.text = getString(R.string.load_error)
                    binding.tvLoadImage.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.pbLoadImage.visibility = View.GONE
                    binding.tvLoadImage.visibility = View.GONE
                    return false
                }

            })
            .into(binding.ivSearchedImage);
    }
}