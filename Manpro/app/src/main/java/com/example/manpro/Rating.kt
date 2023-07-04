package com.example.manpro

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class Rating : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        val db = FirebaseFirestore.getInstance()
        val dataintent=intent.getStringExtra("Nama")
        val constraint=findViewById<ConstraintLayout>(R.id.bgapp)
        val angka = findViewById<EditText>(R.id.rate)
        val Sub = findViewById<Button>(R.id.butsubmit)
        var sumething = arrayListOf<String>()
        var sumething2 = arrayListOf<Int>()
        var sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")



        Sub.setOnClickListener {
            var map = hashMapOf<String, Any>()
            var Rat =0.0
            var Rev =0
            db.collection("User")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        if(document.data.get("nama").toString() == text.toString()) {
                            sumething = document.data.get("rateName") as ArrayList<String>
                            sumething2 = document.data.get("rateValue") as ArrayList<Int>
                            if (sumething.isNullOrEmpty()){
                                sumething.add(dataintent.toString())
                                sumething2.add(angka.text.toString().toInt())
                                val datainput = Data(
                                    document.data.get("email").toString(),
                                    document.data.get("nama").toString(),
                                    document.data.get("pass").toString(),
                                    document.data.get("book") as ArrayList<String>,
                                    sumething2,
                                    sumething
                                )
                                db.collection("User").document(text.toString()).set(datainput)
                                Log.d("namaMuseum", Rev.toString().toInt().toString())
                                Log.d("namaMuseum", Rat.toString().toDouble().toString())
                                db.collection("list museum")
                                    .get()
                                    .addOnSuccessListener { res->
                                        for (documen in res){
                                            if (documen.data.get("Id")== dataintent.toString()) {
                                                Rat = documen.data.get("Rating").toString().toDouble()
                                                Rev = documen.data.get("Review").toString().toInt()
                                            }
                                        }
                                        var Nrate=(Rat*Rev+angka.text.toString().toInt())/(Rev+1)
                                        var Nr = customFormat(Nrate)
                                        var Rev2=Rev+1
                                        map.put("Rating",Nr)
                                        map.put("Review",Rev2)
                                        Log.d("namaMusum4",map.toString())
                                        Log.d("namaMusum5", Rev2.toString().toInt().toString())
                                        Log.d("namaMusum2", Nr.toDouble().toString())
                                        db.collection("list museum").document(dataintent.toString())
                                            .set(map , SetOptions.merge())
                                    }

                            }else {
                                for ( i in sumething){
                                    Log.d("luar loop", "gk masuk")
                                    //untuk tidak ada
                                    if (i != dataintent.toString() && i == sumething[sumething.size-1]) {
                                        sumething.add(dataintent.toString())
                                        sumething2.add(angka.text.toString().toInt())
                                        val datainput = Data(
                                            document.data.get("email").toString(),
                                            document.data.get("nama").toString(),
                                            document.data.get("pass").toString(),
                                            document.data.get("book") as ArrayList<String>,
                                            sumething2,
                                            sumething
                                        )
                                        db.collection("User")
                                            .document(text.toString())
                                            .set(datainput)
                                        db.collection("list museum")
                                            .get()
                                            .addOnSuccessListener { res ->
                                                for (documen in res) {
                                                    if (documen.data.get("Id") == dataintent.toString()) {
                                                        Rat = documen.data.get("Rating").toString()
                                                            .toDouble()
                                                        Rev = documen.data.get("Review").toString()
                                                            .toInt()
                                                        Log.d("Rating", Rat.toString())
                                                        Log.d("Rating", Rev.toString())
                                                        var Nrate=(Rat*Rev+angka.text.toString().toInt())/(Rev+1)
                                                        var Nr = customFormat(Nrate)
                                                        var Rev2=Rev+1
                                                        map.put("Rating",Nr)
                                                        map.put("Review",Rev2)
                                                        db.collection("list museum").document(dataintent.toString())
                                                            .set(map , SetOptions.merge())
                                                    }
                                                }
                                            }

                                        break
                                    }
                                    //untuk ada
                                    else if (i == dataintent.toString()){
                                        val datainput = Data(
                                            document.data.get("email").toString(),
                                            document.data.get("nama").toString(),
                                            document.data.get("pass").toString(),
                                            document.data.get("book") as ArrayList<String>,
                                            sumething2,
                                            sumething
                                        )
                                        db.collection("list museum")
                                            .get()
                                            .addOnSuccessListener { res ->
                                                for (doc in res) {
                                                    if (doc.data.get("Id") == dataintent.toString()) {
                                                        Rev = doc.data.get("Review").toString().toInt()
                                                        Rat = doc.data.get("Rating").toString().toDouble()

                                                        for (i in document.data.get("rateName") as ArrayList<String>) {
                                                            if (sumething[sumething.indexOf(i)] == dataintent.toString()) {
                                                                sumething2.set(sumething.indexOf(i),angka.text.toString().toInt())
                                                                var Nrate =
                                                                    (Rat * Rev - sumething2[sumething.indexOf(i)]+angka.text.toString().toInt())/ Rev
                                                                var Nr = customFormat(Nrate)
                                                                Log.d("namaMusum3",sumething2[sumething.indexOf(i)].toString())
                                                                map.put("Rating", Nr)

                                                                db.collection("User")
                                                                    .document(text.toString())
                                                                    .set(datainput)
                                                                db.collection("list museum")
                                                                    .document(dataintent.toString())
                                                                    .set(
                                                                        map as Map<String, Any>,
                                                                        SetOptions.merge()
                                                                    )
                                                                break
                                                            }
                                                        }

                                                    }

                                                }

                                            }
                                        /*hash.put("rateName", angka.text.toString().toInt())
                                        db.collection("User").document(text.toString())
                                            .set(hash, SetOptions.merge())*/
                                    }
                                }
                            }
                        }
                    }
                }
            val b = Intent(this@Rating,DetailMuseum::class.java)

            b.putExtra("Id", dataintent.toString())
            startActivity(b)
        }

        db.collection("list museum")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data.get("Id").toString() == dataintent.toString()) {
                        Log.d("namaMuseum", dataintent.toString())
                        Log.d("RatingMuseum", document.data.get("Rating").toString())
//                        namaMuseum.text = document.data.get("Id").toString()
//                        lokasiMuseum.text = document.data.get("Lokasi").toString()
//                        ratingMuseum.text =
//                            document.data.get("Rating").toString().toDouble().toString()
//                        reviewMuseum.text = document.data.get("Review").toString()
//                        tipeMuseum.text = document.data.get("TipeMuseum").toString()
//                        DeskripsiMuseum.text = document.data.get("Deskripsi Museum").toString()
                        val imageUrl = document.data.get("ImageDetailUrl").toString()
                        val storageReference = FirebaseStorage.getInstance().getReference("preview/" + document.data.get("Id").toString() + ".jpg")
                        val localFile = File.createTempFile("image", "jpg")
                        storageReference.getFile(localFile)
                            .addOnSuccessListener { taskSnapshot ->
                                // Image download successful, set it as the background of the root layout
                                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                val drawable = BitmapDrawable(resources, bitmap)
                                constraint.background = drawable
                            }
                            .addOnFailureListener { exception ->
                                // Handle any errors that occur during the download.
                                Log.e("Firebase Storage", "Image download failed: $exception")
                            }
                        //audioUrl = document.data.get("audio url").toString()
                    }
                }
            }

    }
    private fun customFormat(number: Double): String{
        var temp = number - number.toInt()
        temp *= 100
        var nrDec = 2
        if (number <= 0.0){
            return "0.00"
        }
        if (temp >= 1.0){
            val format = NumberFormat.getInstance(Locale.US)
            format.maximumFractionDigits = nrDec
            return format.format(number)
        }
        while (temp < 1.0 && nrDec < 15){
            temp *= 10
            nrDec ++
        }
        if((temp * 10 % 10) != 0.0){
            nrDec++
        }
        val format = NumberFormat.getInstance(Locale.US)
        format.maximumFractionDigits = nrDec
        return format.format(number)
    }
}