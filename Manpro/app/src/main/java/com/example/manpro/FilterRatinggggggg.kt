package com.example.manpro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FilterRatinggggggg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_ratinggggggg)

        var listMuseum = ArrayList<Museum>()
        val db = FirebaseFirestore.getInstance()
        val dataintent=intent.getStringExtra("Nama")
        val constraint=findViewById<ConstraintLayout>(R.id.bgapp)
        val angka = findViewById<EditText>(R.id.rate)
        val Sub = findViewById<Button>(R.id.butsubmit)
        var sumething = arrayListOf<String>()
        var sumething2 = arrayListOf<Int>()
        var sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        var datain = intent.getStringExtra("Rate")
        setContentView(R.layout.activity_filter_ratinggggg)
        setContentView(R.layout.activity_filter_rating)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2rtg)
        val filterNam = findViewById<Button>(R.id.button5rtg)
        val filterRat = findViewById<Button>(R.id.button4rtg)
        val Tb= findViewById<EditText>(R.id.Ratingedittext2)
        val up = findViewById<ImageView>(R.id.imageView5)
        val down = findViewById<ImageView>(R.id.imageView7)
        val btn = findViewById<Button>(R.id.button2rtg)
        val Searchrtg = findViewById<EditText>(R.id.Searchrtg)
        val profile = findViewById<ImageView>(R.id.imageView3)
        btn.setOnClickListener {
            val b = Intent(this@FilterRatinggggggg,SearchActivity::class.java)
            b.putExtra("Di",Searchrtg.text.toString())
            startActivity(b)
        }
//        profile.setOnClickListener{
//            val b = Intent(this@FilterRatinggggggg,Profile::class.java)
//            b.putExtra("usrname",text.toString())
//            startActivity(b)
//        }
        up.setOnClickListener {
            val b = Intent(this@FilterRatinggggggg,FilterRatinggggg::class.java)
            Log.d("kalkalkal",Tb.text.toString())
            b.putExtra("Rate",Tb.text.toString())
            startActivity(b)
        }

        down.setOnClickListener {
            val b = Intent(this@FilterRatinggggggg,FilterRatinggggggg::class.java)
            Log.d("kalkalkal",Tb.text.toString())
            b.putExtra("Rate",Tb.text.toString())
            startActivity(b)
        }


        db.collection("list museum").orderBy("Rating", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("kal", datain.toString().toDouble().toString())
                    if (document.data.get("Rating").toString().toDouble() <=  datain.toString().toDouble()){
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
                }
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = AdapterMuseum(listMuseum)
            }

        filterNam.setOnClickListener{
            val b = Intent(this@FilterRatinggggggg,FilterJarak::class.java)
            startActivity(b)
        }

        filterRat.setOnClickListener{
            val b = Intent(this@FilterRatinggggggg,MainActivity::class.java)
            startActivity(b)
        }

    }
}