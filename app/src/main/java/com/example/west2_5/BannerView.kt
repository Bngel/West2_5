package com.example.west2_5

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_banner.view.*

class BannerView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs){
    private var lastPosition = 0
    private val images= ArrayList<Int>()
    private val mHandler = Handler()

    init {
        LayoutInflater.from(context).inflate(R.layout.view_banner, this)

    }

    private fun initIndicatorDots(){
        for (i in images.indices) {
            val dot = ImageView(context)
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

    fun flush(){
        mHandler.postDelayed(runnable, 5000)
    }

    private val runnable: Runnable = object : Runnable {
        override fun run() {
            var currentPosition = bannerView.currentItem
            currentPosition += 1
            bannerView.setCurrentItem(currentPosition, true)
            mHandler.postDelayed(this, 5000)
        }
    }

    fun initImage(imgs: List<Int>) {
        images.addAll(imgs)
        val viewPagerAdapter = ViewPagerAdapter(images)
        bannerView.adapter = viewPagerAdapter
        bannerView.currentItem = 500
        lastPosition = 500
        initIndicatorDots()
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
}