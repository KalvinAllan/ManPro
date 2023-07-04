package com.example.manpro

import android.content.Context
import android.content.Intent
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
import com.google.firebase.firestore.Query

class FilterJarak : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    val db = FirebaseFirestore.getInstance()
    private var listMuseum = arrayListOf<Museum>()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_jarak)
        val filterRating = findViewById<Button>(R.id.button4jrk)
        val filterJarak =  findViewById<Button>(R.id.button5jrk)
        var sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        recyclerView = findViewById(R.id.recyclerView2jrk)
        val Tb= findViewById<EditText>(R.id.Ratingedittext2)
        val up = findViewById<ImageView>(R.id.imageView5)
        val down = findViewById<ImageView>(R.id.imageView7)
        val profile = findViewById<ImageView>(R.id.imageView3)
        val search = findViewById<EditText>(R.id.Searchjrk)

        search.setOnClickListener {
            val b = Intent(this@FilterJarak,SearchActivity::class.java)
            b.putExtra("Di",search.text.toString())
            startActivity(b)
        }

        up.setOnClickListener {
            val b = Intent(this@FilterJarak,FilterRatinggggg::class.java)
            Log.d("kalkalkal",Tb.text.toString())
            b.putExtra("Rate",Tb.text.toString())
            startActivity(b)
        }

        profile.setOnClickListener{
            val b = Intent(this@FilterJarak,Profile::class.java)
            b.putExtra("usrname",text.toString())
            startActivity(b)
        }

        down.setOnClickListener {
            val b = Intent(this@FilterJarak,FilterRatinggggggg::class.java)
            Log.d("kalkalkal",Tb.text.toString())
            b.putExtra("Rate",Tb.text.toString())
            startActivity(b)
        }

        db.collection("list museum").orderBy("Id", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("kal",document.get("Id").toString())
                    Log.d("kal",document.get("Lokasi").toString())
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

        filterRating.setOnClickListener{
            val b = Intent(this@FilterJarak,FilterRating::class.java)
            startActivity(b)
        }

        filterJarak.setOnClickListener{
            val b = Intent(this@FilterJarak,MainActivity::class.java)
            startActivity(b)
        }


    }
}