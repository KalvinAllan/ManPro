package com.example.manpro

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.VirtualLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class ViltuarTour : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viltuar_tour)
        val storageRef: StorageReference = FirebaseStorage.getInstance().reference
        val foto = findViewById<ImageView>(R.id.imagepath)
        val btnkiri = findViewById<Button>(R.id.button10)
        val btnatas = findViewById<Button>(R.id.button11)
        val btnkanan = findViewById<Button>(R.id.button12)
        val btnbawah = findViewById<Button>(R.id.button13)
        val dataintent=intent.getStringExtra("Nama")
        var X =intent.getStringExtra("X")
        var Y =intent.getStringExtra("Y")
        val db = FirebaseFirestore.getInstance()
        val kol = findViewById<Button>(R.id.toKoleksi)
        val bck = findViewById<TextView>(R.id.textView14)

        bck.setOnClickListener {
            val b = Intent(this@ViltuarTour,DetailMuseum::class.java)
            b.putExtra("Id",dataintent)
            startActivity(b)
        }

        kol.setOnClickListener {
            val b = Intent(this@ViltuarTour,KoleksiWR::class.java)
            b.putExtra("Nama",dataintent)
            startActivity(b)
        }


        if (X==null ){
            X=0.toString()
        }
        if (Y==null){
            Y=0.toString()
        }
        if (X.toString().toInt()==0 && Y.toString().toInt()==0){
            db.collection("list museum")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Id").toString() == dataintent.toString()) {
                            var storageReference = FirebaseStorage.getInstance()
                                .getReference("Museum WR Soepratman").child("route").child("wr1.jpg")
                            val localFile = File.createTempFile("image22", "jpg")
                            storageReference.getFile(localFile)
                                .addOnSuccessListener { taskSnapshot ->
                                    // Image download successful, set it as the background of the root layout
                                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                    val drawable = BitmapDrawable(resources, bitmap)
                                    foto.setImageBitmap(bitmap)
                                }
                                .addOnFailureListener { exception ->
                                    // Handle any errors that occur during the download.
                                    Log.e("Firebase Storage", "Image download failed: $exception")
                               }
                        }
                    }
                }
            btnbawah.isEnabled=false
            btnatas.isEnabled=true
            btnkanan.isEnabled= false
            btnkiri.isEnabled=false
        }else if (X.toString().toInt()==1 && Y.toString().toInt()==0){
            db.collection("list museum")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Id").toString() == dataintent.toString()) {
                            var storageReference = FirebaseStorage.getInstance()
                                .getReference("Museum WR Soepratman").child("route").child("wr2.jpg")
                            val localFile = File.createTempFile("image23", "jpg")
                            storageReference.getFile(localFile)
                                .addOnSuccessListener { taskSnapshot ->
                                    // Image download successful, set it as the background of the root layout
                                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                    val drawable = BitmapDrawable(resources, bitmap)
                                    foto.setImageBitmap(bitmap)
                                }
                                .addOnFailureListener { exception ->
                                    // Handle any errors that occur during the download.
                                    Log.e("Firebase Storage", "Image download failed: $exception")
                                }
                        }
                    }
                }
            btnbawah.isEnabled=true
            btnatas.isEnabled=true
            btnkanan.isEnabled= false
            btnkiri.isEnabled=false
        }else if (X.toString().toInt()==2 && Y.toString().toInt()==0){
            db.collection("list museum")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Id").toString() == dataintent.toString()) {
                            var storageReference = FirebaseStorage.getInstance()
                                .getReference("Museum WR Soepratman").child("route").child("wr3.jpg")
                            val localFile = File.createTempFile("image24", "jpg")
                            storageReference.getFile(localFile)
                                .addOnSuccessListener { taskSnapshot ->
                                    // Image download successful, set it as the background of the root layout
                                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                    val drawable = BitmapDrawable(resources, bitmap)
                                    foto.setImageBitmap(bitmap)
                                }
                                .addOnFailureListener { exception ->
                                    // Handle any errors that occur during the download.
                                    Log.e("Firebase Storage", "Image download failed: $exception")
                                }
                        }
                    }
                }
            btnbawah.isEnabled=true
            btnatas.isEnabled=true
            btnkanan.isEnabled= true
            btnkiri.isEnabled=true
        }else if (X.toString().toInt()==2 && Y.toString().toInt()==-1){
            db.collection("list museum")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Id").toString() == dataintent.toString()) {
                            var storageReference = FirebaseStorage.getInstance()
                                .getReference("Museum WR Soepratman").child("route").child("wr4left.jpg")
                            val localFile = File.createTempFile("image25", "jpg")
                            storageReference.getFile(localFile)
                                .addOnSuccessListener { taskSnapshot ->
                                    // Image download successful, set it as the background of the root layout
                                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                    val drawable = BitmapDrawable(resources, bitmap)
                                    foto.setImageDrawable(drawable)
                                }
                                .addOnFailureListener { exception ->
                                    // Handle any errors that occur during the download.
                                    Log.e("Firebase Storage", "Image download failed: $exception")
                                }
                        }
                    }
                }
            btnbawah.isEnabled=false
            btnatas.isEnabled=false
            btnkanan.isEnabled= true
            btnkiri.isEnabled=false
        }else if (X.toString().toInt()==2 && Y.toString().toInt()==1){
            db.collection("list museum")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Id").toString() == dataintent.toString()) {
                            var storageReference = FirebaseStorage.getInstance()
                                .getReference("Museum WR Soepratman").child("route").child("wr5 dpn.jpg")
                            val localFile = File.createTempFile("image26", "jpg")
                            storageReference.getFile(localFile)
                                .addOnSuccessListener { taskSnapshot ->
                                    // Image download successful, set it as the background of the root layout
                                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                    val drawable = BitmapDrawable(resources, bitmap)
                                    foto.setImageDrawable(drawable)
                                }
                                .addOnFailureListener { exception ->
                                    // Handle any errors that occur during the download.
                                    Log.e("Firebase Storage", "Image download failed: $exception")
                                }
                        }
                    }
                }
            btnbawah.isEnabled=false
            btnatas.isEnabled=false
            btnkanan.isEnabled= false
            btnkiri.isEnabled=true
        }else if (X.toString().toInt()==3 && Y.toString().toInt()==0){
            db.collection("list museum")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (document.data.get("Id").toString() == dataintent.toString()) {
                            var storageReference = FirebaseStorage.getInstance()
                                .getReference("Museum WR Soepratman").child("route").child("wr5 blkg.jpg")
                            val localFile = File.createTempFile("image27", "jpg")
                            storageReference.getFile(localFile)
                                .addOnSuccessListener { taskSnapshot ->
                                    // Image download successful, set it as the background of the root layout
                                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                    val drawable = BitmapDrawable(resources, bitmap)
                                    foto.setImageBitmap(bitmap)
                                }
                                .addOnFailureListener { exception ->
                                    // Handle any errors that occur during the download.
                                    Log.e("Firebase Storage", "Image download failed: $exception")
                                }
                        }
                    }
                }
            btnbawah.isEnabled=true
            btnatas.isEnabled=false
            btnkanan.isEnabled= false
            btnkiri.isEnabled=false
        }

        btnatas.setOnClickListener {
            val b = Intent(this@ViltuarTour,ViltuarTour::class.java)
            b.putExtra("X",(X.toString().toInt()+1).toString())
            b.putExtra("Nama",dataintent.toString())
            b.putExtra("Y",(Y.toString().toInt()).toString())
            startActivity(b)
        }
        btnbawah.setOnClickListener {
            val b = Intent(this@ViltuarTour,ViltuarTour::class.java)
            b.putExtra("X",(X.toString().toInt()-1).toString())
            b.putExtra("Nama",dataintent.toString())
            b.putExtra("Y",(Y.toString().toInt()).toString())
            startActivity(b)
        }
        btnkanan.setOnClickListener {
            val b = Intent(this@ViltuarTour,ViltuarTour::class.java)
            b.putExtra("X",(X.toString().toInt()).toString())
            b.putExtra("Nama",dataintent.toString())
            b.putExtra("Y",(Y.toString().toInt()+1).toString())
            startActivity(b)
        }
        btnkiri.setOnClickListener {
            val b = Intent(this@ViltuarTour,ViltuarTour::class.java)
            b.putExtra("X",(X.toString().toInt()).toString())
            b.putExtra("Nama",dataintent.toString())
            b.putExtra("Y",(Y.toString().toInt()-1).toString())
            startActivity(b)
        }

    }

}