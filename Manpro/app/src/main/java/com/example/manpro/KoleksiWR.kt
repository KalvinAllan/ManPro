package com.example.manpro

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class KoleksiWR : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koleksi_wr)

        val foto = findViewById<ImageView>(R.id.imageView6)
        val btnback = findViewById<Button>(R.id.button14)
        val btnforward = findViewById<Button>(R.id.button15)
        val dataintent = intent.getStringExtra("Nama")
        var countItem = 1
        var inten = intent.getStringExtra("count")
        var tmblback = findViewById<TextView>(R.id.textView14)

        if (inten != null) {
            if (inten.isNotEmpty()){
                countItem = inten.toString().toInt()
            }
        }
        /*if (!dataintent.isNullOrEmpty()){
            countItem = dataintent.toString().toInt()
        }*/
        if (dataintent.toString() == "Museum WR Soepratman") {
            if (countItem == 1){
                btnback.isEnabled = false
            }
            else if (countItem == 24){
                btnforward.isEnabled = false
            }
            else {
                btnback.isEnabled = true
                btnforward.isEnabled = true
            }
            var pathfoto = "$dataintent $countItem.jpg"
            var storageReference = FirebaseStorage.getInstance()
                .getReference("Museum WR Soepratman").child("koleksi").child(pathfoto)
            val localFile = File.createTempFile("image22", "jpg")
            storageReference.getFile(localFile)
                .addOnSuccessListener { taskSnapshot ->
                    // Image download successful, set it as the background of the root layout
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    val drawable = BitmapDrawable(resources, bitmap)
                    foto.setImageBitmap(bitmap)
                }
            tmblback.setOnClickListener{
                val b = Intent(this@KoleksiWR,ViltuarTour::class.java)
                b.putExtra("X", 0)
                b.putExtra("Y", 0)
                b.putExtra("Nama", dataintent)
                startActivity(b)
            }
        } else if (dataintent.toString() == "H.O.S Tjokroaminoto") {
            if (countItem == 1){
                btnback.isEnabled = false
            }
            else if (countItem == 44){
                btnforward.isEnabled = false
            }
            else {
                btnback.isEnabled = true
                btnforward.isEnabled = true
            }
            var pathfoto = "$dataintent $countItem.jpg"
            var storageReference = FirebaseStorage.getInstance()
                .getReference("H.O.S Tjokroaminoto").child("koleksi").child(pathfoto)
            val localFile = File.createTempFile("image22", "jpg")
            storageReference.getFile(localFile)
                .addOnSuccessListener { taskSnapshot ->
                    // Image download successful, set it as the background of the root layout
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    val drawable = BitmapDrawable(resources, bitmap)
                    foto.setImageBitmap(bitmap)
                }
            tmblback.setOnClickListener{
                val b = Intent(this@KoleksiWR,VTHOS::class.java)
                b.putExtra("X", 0)
                b.putExtra("Y", 0)
                b.putExtra("Nama", dataintent)
                startActivity(b)
            }
        }else if (dataintent.toString() == "Museum Dr. Soetomo"){
            if (countItem == 1){
                btnback.isEnabled = false
            }
            else if (countItem == 37){
                btnforward.isEnabled = false
            }
            else {
                btnback.isEnabled = true
                btnforward.isEnabled = true
            }
            var pathfoto = "$dataintent $countItem.jpg"
            var storageReference = FirebaseStorage.getInstance()
                .getReference("Museum Dr. Soetomo").child("koleksi").child(pathfoto)
            val localFile = File.createTempFile("image22", "jpg")
            storageReference.getFile(localFile)
                .addOnSuccessListener { taskSnapshot ->
                    // Image download successful, set it as the background of the root layout
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    val drawable = BitmapDrawable(resources, bitmap)
                    foto.setImageBitmap(bitmap)
                }

            tmblback.setOnClickListener{
                val b = Intent(this@KoleksiWR,ViltuarTour2::class.java)
                b.putExtra("X", 0)
                b.putExtra("Y", 0)
                b.putExtra("Nama", dataintent)
                startActivity(b)
            }
        }

        btnforward.setOnClickListener {
            val b = Intent(this@KoleksiWR,KoleksiWR::class.java)
            b.putExtra("count",(countItem+1).toString())
            b.putExtra("Nama", dataintent)
            startActivity(b)
        }

        btnback.setOnClickListener {
            val b = Intent(this@KoleksiWR,KoleksiWR::class.java)
            b.putExtra("count",(countItem-1).toString())
            b.putExtra("Nama", dataintent)
            startActivity(b)
        }
    }
}