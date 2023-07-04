package com.example.manpro

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File

class FilterRatinggg : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    val db = FirebaseFirestore.getInstance()
    private var listMuseum = arrayListOf<Museum>()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_ratinggg)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        val search = findViewById<EditText>(R.id.Search)
        var btnsearch = findViewById<Button>(R.id.button2)
        val filterRating = findViewById<Button>(R.id.button4)
        var sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        val filterNam = findViewById<Button>(R.id.button5)
        val profile = findViewById<ImageView>(R.id.imageView3)
        val Tb= findViewById<EditText>(R.id.Ratingedittext2)
        val up = findViewById<ImageView>(R.id.imageView5)
        val down = findViewById<ImageView>(R.id.imageView7)

//        if (Tb.text.toString().toInt()!=0){
//            up.setOnClickListener {
//                val b = Intent(this@FilterRatinggg,FilterRatinggggg::class.java)
//                b.putExtra("Rate",Tb.toString().toInt())
//                startActivity(b)
//            }
//        }
        up.setOnClickListener {
            val b = Intent(this@FilterRatinggg,FilterRatinggggg::class.java)
            Log.d("kalkalkal",Tb.text.toString())
                b.putExtra("Rate",Tb.text.toString())
                startActivity(b)
        }

        down.setOnClickListener {
            val b = Intent(this@FilterRatinggg,FilterRatinggggggg::class.java)
            Log.d("kalkalkal",Tb.text.toString())
            b.putExtra("Rate",Tb.text.toString())
            startActivity(b)
        }


        profile.setOnClickListener{
            val b = Intent(this@FilterRatinggg,Profile::class.java)
            b.putExtra("usrname",text.toString())
            startActivity(b)
        }

        btnsearch.setOnClickListener {
            val b = Intent(this@FilterRatinggg,SearchActivity::class.java)
            b.putExtra("Di",search.text.toString())
            startActivity(b)
        }

        filterRating.setOnClickListener{
            val b = Intent(this@FilterRatinggg,FilterRating::class.java)
            startActivity(b)
        }
        filterNam.setOnClickListener{
            val b = Intent(this@FilterRatinggg,FilterJarak::class.java)
            startActivity(b)
        }
        SiapkanData()


    }


    private fun SiapkanData(){0
        db.collection("list museum")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("kal",document.get("Id").toString())
                    Log.d("kal",document.get("Lokasi").toString())

                    val localFile = File.createTempFile("image", "jpg")
                    var bitemap: Bitmap? = null
                    var drawable= BitmapDrawable(resources, bitemap)
//                    storageReference.getFile(localFile)
//                        .addOnSuccessListener { taskSnapshot ->
//                            // Image download successful, set it as the background of the root layout
//                            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
//                            drawable= BitmapDrawable(resources, bitmap)
//                        }
//                        .addOnFailureListener { exception ->
//                            // Handle any errors that occur during the download.
//                            Log.e("Firebase Storage", "Image download failed: $exception")
//                        }
                    Log.d("maluli", drawable.toString())
                    if (document.data.get("Id").toString()=="Blockbuster Museum"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.blockbuster_museum)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="H.O.S Tjokroaminoto"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.hos_tjokroaminoto)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="House of Sampoerna"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.house_of_sampoerna)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Bank Indonesia"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_bank_indonesia)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Dr. Soetomo"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_dr_soetomo)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Kanker Indonesia"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_kanker_indonesia)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Kesehatan Dr. Adhyatma"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_kesehatan_dr_adhyatma)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Olahraga Surabaya"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_olahraga_surabaya)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Pendidikan Surabaya"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_pendidikan_surabaya)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum Sepuluh Nopember"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_sepuluh_nopember)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Museum WR Soepratman"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_wr_soepratman)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Siola Surabaya Museum"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.siola_surabaya_museum)
                        listMuseum.add(msm)
                    }
                    if (document.data.get("Id").toString()=="Teknoform Museum"){
                        val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.teknoform_museum)
                        listMuseum.add(msm)
                    }

                }
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = AdapterMuseum(listMuseum)
            }
    }
}