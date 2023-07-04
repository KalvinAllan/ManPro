package com.example.manpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore



class SearchActivity : AppCompatActivity() {

    private var listMuseum = arrayListOf<Museum>()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        recyclerView = findViewById(R.id.rv)
        recyclerView.setHasFixedSize(true)
        val searc = findViewById<TextView>(R.id.Serachresult)
        val dataintent = intent.getStringExtra("Di")
        val nama = findViewById<EditText>(R.id.Searchjrk)
        val searche =findViewById<Button>(R.id.button2src)

        if( dataintent.toString() == ""){
            searc.setText("Hasil pencarian tanpa kata kunci")
        }
        else {
            searc.setText("Hasil pencarian dengan kata kunci "+"'"+ dataintent.toString()+ "'")
        }
        Log.d("hahahaha",dataintent.toString())
       db.collection("list museum")
            .get()
            .addOnSuccessListener { result ->
               for (document in result) {
                   if(document.data.get("Id").toString().contains(dataintent.toString(), ignoreCase = true) ||document.data.get("TipeMuseum").toString().contains(dataintent.toString(), ignoreCase = true) ){
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
            .addOnFailureListener {
                Log.d("hihihihi",dataintent.toString())
            }
        searche.setOnClickListener {
            if (nama.text.toString() != null){
                val b = Intent(this@SearchActivity,SearchActivity::class.java)
                b.putExtra("Di",nama.text.toString())
                startActivity(b)
            }
        }
    }

}
