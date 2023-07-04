package com.example.manpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView

class Opening : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        val gambar = findViewById<ImageView>(R.id.imageView2)

        gambar.setOnClickListener {
            val intent = Intent(this@Opening,Register::class.java)
            startActivity(intent)
        }

        object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                val intent = Intent(this@Opening,Register::class.java)
                startActivity(intent)
            }
        }.start()
    }
}