package com.example.insightsurabaya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.cardview.widget.CardView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var imageSlider = findViewById<ImageSlider>(R.id.carosel)
        var slidemodel = ArrayList<SlideModel>()
        var box = findViewById<CardView>(R.id.cardView)
        var test = findViewById<EditText>(R.id.searchmuseum)

        slidemodel.add(SlideModel(R.drawable.kapalselam, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.patung, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.sampoerna, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.wr, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.sepuluhnov, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.siola, scaleType = ScaleTypes.FIT))

        imageSlider.setImageList(slidemodel, ScaleTypes.FIT)

        test.setOnClickListener{
            val intent = Intent(this@Home,maps_testing::class.java)
            startActivity(intent)
        }
    }
}