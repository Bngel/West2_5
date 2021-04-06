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
        initIndicatorDots()

        val viewPagerAdapter = ViewPagerAdapter(images)
        bannerView.adapter = viewPagerAdapter
        bannerView.currentItem = 500
        lastPosition = 500

        bannerView.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val current = position % 4
                val last = lastPosition % 4

                val curChild = indicatorView.getChildAt(current) as ImageView
                curChild.setImageResource(R.drawable.blue)
                val lastChild = indicatorView.getChildAt(last) as ImageView
                lastChild.setImageResource(R.drawable.white)
                lastPosition = position
                mHandler.removeCallbacks(runnable)
            }
        })
    }

    private fun initImages(){
        images.add(R.drawable.img1)
        images.add(R.drawable.img2)
        images.add(R.drawable.img3)
        images.add(R.drawable.img4)
    }

    private fun initIndicatorDots(){
        for (i in images.indices) {
            val dot = ImageView(this)
            if (i == 0)
                dot.setImageResource(R.drawable.blue)
            else
                dot.setImageResource(R.drawable.white)
            val layoutParams = LinearLayout.LayoutParams(
                20,
                20
            )
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.marginEnd = 10
            }
            if (dot.parent != null)
                (dot.parent as ViewGroup).removeView(dot)
            dot.layoutParams = layoutParams
            indicatorView.addView(dot)
        }
    }

    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(runnable, 5000)
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(runnable)
    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            var currentPosition = bannerView.currentItem
            currentPosition += 1
            bannerView.setCurrentItem(currentPosition, true)
            mHandler.postDelayed(this, 5000)
        }
    }
}