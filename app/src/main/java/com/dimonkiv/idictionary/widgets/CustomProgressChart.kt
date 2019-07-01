package com.dimonkiv.idictionary.widgets

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.dimonkiv.idictionary.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class CustomProgressChart(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet) {
    private lateinit var progressBar: CircularProgressBar
    private lateinit var titleTV: TextView


    override fun onFinishInflate() {
        super.onFinishInflate()
        (context as Activity).layoutInflater.inflate(R.layout.view_custom_progress, this)
        initUI()
    }

    private fun initUI() {
        progressBar = findViewById(R.id.progress_bar)
        titleTV = findViewById(R.id.title_tv)
    }

    fun setProgress(progress: Int) {
        titleTV.text = "$progress%"
        progressBar.setProgressWithAnimation(progress.toFloat(), 2500)


        if (progress > 50) {
            setPositiveProgressStyle()
            return
        }

        setNegativeProgressStyle()
    }

    private fun setPositiveProgressStyle() {
        progressBar.color = ContextCompat.getColor(context, R.color.colorPositiveProgressValue)
        progressBar.backgroundColor = ContextCompat.getColor(context, R.color.colorPositiveProgressBackground)
    }

    private fun setNegativeProgressStyle() {
        progressBar.color = ContextCompat.getColor(context, R.color.colorNegativeProgressValue)
        progressBar.backgroundColor = ContextCompat.getColor(context, R.color.colorNegativeProgressBackground)
    }
}