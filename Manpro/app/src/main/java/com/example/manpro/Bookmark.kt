package com.example.manpro

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Bookmark : AppCompatActivity() {
    private var listMuseum = arrayListOf<Museum>()
    private var bookList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        val db = FirebaseFirestore.getInstance()

        val recview = findViewById<RecyclerView>(R.id.recyclerView2book)
        val sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")

        db.collection("User")
            .get()
            .addOnSuccessListener { result ->
                for (documentt in result) {
                    if (documentt.data.get("nama").toString() == text.toString()){
                        bookList = documentt.data.get("book") as ArrayList<String>
                            }
                        }
                db.collection("list museum").orderBy("Id", Query.Direction.ASCENDING)
                    .get()
                    .addOnSuccessListener { resultmus ->
                        for (document in resultmus) {
                            for (book in bookList) {
                                if (document.data.get("Id").toString() == book) {
                                    if (document.data.get("Id")
                                            .toString() == "Blockbuster Museum"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.blockbuster_museum
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "H.O.S Tjokroaminoto"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.hos_tjokroaminoto
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "House of Sampoerna"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.house_of_sampoerna
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Bank Indonesia"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_bank_indonesia
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Dr. Soetomo"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_dr_soetomo
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Kanker Indonesia"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_kanker_indonesia
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Kesehatan Dr. Adhyatma"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_kesehatan_dr_adhyatma
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Olahraga Surabaya"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_olahraga_surabaya
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Pendidikan Surabaya"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_pendidikan_surabaya
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum Sepuluh Nopember"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_sepuluh_nopember
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Museum WR Soepratman"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.museum_wr_soepratman
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id")
                                            .toString() == "Siola Surabaya Museum"
                                    ) {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.siola_surabaya_museum
                                        )
                                        listMuseum.add(msm)
                                    }
                                    if (document.data.get("Id").toString() == "Teknoform Museum") {
                                        val msm = Museum(
                                            document.getString("Id").toString(),
                                            document.getString("Lokasi").toString(),
                                            document.get("Rating").toString().toDouble(),
                                            document.get("Review").toString().toInt(),
                                            document.getString("TipeMuseum").toString(),
                                            R.drawable.teknoform_museum
                                        )
                                        listMuseum.add(msm)
                                    }

                                }
                                recview.layoutManager = LinearLayoutManager(this)
                                recview.adapter = AdapterMuseum(listMuseum)
                            }
                        }
                    }
                    }
    }
}