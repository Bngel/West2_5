package com.example.west2_5

import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var lastPosition = 0
    private val images = ArrayList<Int>()
    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initImages()
        MainBanner.initImage(images)
    }

    private fun initImages(){
        images.add(R.drawable.img1)
        images.add(R.drawable.img2)
        images.add(R.drawable.img3)
        images.add(R.drawable.img4)
    }


    override fun onResume() {
        super.onResume()
        MainBanner.flush()
    }

    override fun onPause() {
        super.onPause()
        MainBanner.flush()
    }
}