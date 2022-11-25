package com.android.ramen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ImageActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var option: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_image)
        imageView = findViewById(R.id.image)
        option = findViewById(R.id.option)

        imageView.setImageResource(R.drawable.ic_circle_black_simple)

        var startX = 0.0f
        var startY = 0.0f
        Log.d("++크기", "${imageView.x}")
        imageView.setOnTouchListener { v, event ->
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }

                MotionEvent.ACTION_MOVE -> {
                    val movedX: Float = event.x - startX
                    val movedY: Float = event.y - startY

                    v.x = v.x + movedX
                    v.y = v.y + movedY
                }
            }
            true
        }
    }


}