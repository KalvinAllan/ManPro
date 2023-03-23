package com.example.insightsurabaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var imageSlider = findViewById<ImageSlider>(R.id.carosel)
        var slidemodel = ArrayList<SlideModel>()

        slidemodel.add(SlideModel(R.drawable.kapalselam, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.patung, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.sampoerna, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.wr, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.sepuluhnov, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.siola, scaleType = ScaleTypes.FIT))

        imageSlider.setImageList(slidemodel, ScaleTypes.FIT)
    }
}