package com.example.imageflipper3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var imageList = intArrayOf(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewFlipper = findViewById<ViewFlipper>(R.id.viewFlipper)
        if (viewFlipper != null) {
            viewFlipper.setInAnimation(applicationContext, android.R.anim.slide_in_left)
            viewFlipper.setOutAnimation(applicationContext, android.R.anim.slide_out_right)
        }

        if (viewFlipper != null) {
            for (image in imageList) {
                val imageView = ImageView(this)
                val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(30, 30, 30, 30)
                layoutParams.gravity = Gravity.CENTER
                imageView.layoutParams = layoutParams
                imageView.setImageResource(image)
                viewFlipper.addView(imageView)
            }
        }

        val btnPlayPause = findViewById<Button>(R.id.playPause)
        if (btnPlayPause != null && viewFlipper != null) {
            btnPlayPause.setOnClickListener {
                if (viewFlipper.isFlipping)
                    viewFlipper.stopFlipping()
                else
                    viewFlipper.startFlipping()

                btnPlayPause.text = getString(if (viewFlipper.isFlipping) R.string.pause else R.string.play)
                Toast.makeText(this@MainActivity, getString(if (viewFlipper.isFlipping) R.string.flipping_started else R.string.flipping_stopped), Toast.LENGTH_SHORT).show()
            }
        }
    }
}