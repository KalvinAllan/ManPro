package com.example.manpro

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.lang.Math.atan2
import java.lang.Math.sin
import java.lang.StrictMath.cos
import java.lang.StrictMath.sqrt
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow


class DetailMuseum : AppCompatActivity() {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private lateinit var jarakMuseum: TextView
    private val PERMISSION_ID = 44
    private lateinit var btnPlay : Button
    var mediaPlayer : MediaPlayer? = null
    var audioUrl = ""
    private var latitude = 0.0
    var longitude = 0.0
    var count = 0
    private val storageRef: StorageReference = FirebaseStorage.getInstance().reference
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_museum)
        /*getUserLocation(applicationContext)*/
        var dataintent = intent.getStringExtra("Id")
        var bookList = ArrayList<String>()
        val db = FirebaseFirestore.getInstance()
        val constraint = findViewById<ConstraintLayout>(R.id.bgapp)
        var Url = ""
        val namaMuseum = findViewById<TextView>(R.id.txtNama)
        val lokasiMuseum = findViewById<TextView>(R.id.txtLokasi)
        val ratingMuseum = findViewById<TextView>(R.id.txtRating)
        val reviewMuseum = findViewById<TextView>(R.id.txtReview)
        var count = 0
        jarakMuseum = findViewById(R.id.txtJarak)
        btnPlay = findViewById(R.id.btnPlay)
        val tipeMuseum = findViewById<TextView>(R.id.txtTipe)
        var bahasa = intent.getStringExtra("Bahasa")
        val btnBook = findViewById<Button>(R.id.btnBook)
        val GMapMuseum = findViewById<TextView>(R.id.txtGoogleMaps)
        val DeskripsiMuseum = findViewById<TextView>(R.id.txtDeskripsi)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val Translate = findViewById<TextView>(R.id.txtTranslate)
        val sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        var con =0
        var araara = findViewById<TextView>(R.id.Description)
        araara.alpha=0f
        val rating= findViewById<ConstraintLayout>(R.id.constraintLayout)
        val koleksi= findViewById<Button>(R.id.txtKoleksi)
        val bck = findViewById<TextView>(R.id.textView14)

        bck.setOnClickListener {
            val b = Intent(this@DetailMuseum,MainActivity::class.java)
            b.putExtra("Id",dataintent)
            startActivity(b)
        }


        rating.setOnClickListener {
            val b = Intent(this@DetailMuseum,Rating::class.java)
            b.putExtra("Nama",dataintent.toString())
            startActivity(b)
        }

        koleksi.setOnClickListener {
            if (dataintent.toString()== "H.O.S Tjokroaminoto"){
                val b = Intent(this@DetailMuseum,VTHOS::class.java)
                b.putExtra("Nama",dataintent.toString())
                startActivity(b)
            }else if (dataintent.toString()=="Museum WR Soepratman"){
                val b = Intent(this@DetailMuseum,ViltuarTour::class.java)
                Log.d("Halsdmaso", dataintent.toString())
                b.putExtra("Nama",dataintent.toString())
                startActivity(b)
            } else if (dataintent.toString()=="Museum Dr. Soetomo"){
                val b = Intent(this@DetailMuseum,ViltuarTour2::class.java)
                Log.d("Halsdmaso", dataintent.toString())
                b.putExtra("Nama",dataintent.toString())
                startActivity(b)
            }
        }

        Translate.setOnClickListener{
//            val b = Intent(this@DetailMuseum,DetailMuseum::class.java)
//            if (bahasa.toString() ==null){b.putExtra("Bahasa","Ing")}
//            if (bahasa.toString() != "Ing"){b.putExtra("Bahasa","Ing")}
//            if (bahasa.toString() == "Ing"){b.putExtra("Bahasa","Ino")}
//            b.putExtra("Id",dataintent.toString())
//            startActivity(b)
            con=con+1
            if (con%2==0){
                araara.alpha=0f
                DeskripsiMuseum.alpha=1f
            }else if (con%2==1){
                DeskripsiMuseum.alpha=0f
                araara.alpha=1f

            }


        }

        btnBook.setOnClickListener {
                db.collection("User")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if (document.data.get("nama").toString()==text.toString()){
                                var email = document.data.get("email").toString()
                                var pass = document.data.get("pass").toString()
                                bookList = document.data.get("book") as ArrayList<String>
                                if (bookList.isNullOrEmpty()){
                                    bookList.add(dataintent.toString())
                                    val datainput = Data(email,text.toString(),pass,bookList,
                                        document.data.get("rateValue") as ArrayList<Int>, document.data.get("rateName") as ArrayList<String>
                                    )
                                    db.collection("User").document(text.toString()).set(datainput)
                                }else {
                                    for (i in bookList.indices){
                                        if (bookList[i] == dataintent.toString()){
                                            bookList.removeAt(i)
                                            count = 0
                                            val datainput = Data(email,text.toString(),pass,bookList,document.data.get("rateValue") as ArrayList<Int>, document.data.get("rateName") as ArrayList<String>)
                                            db.collection("User").document(text.toString()).set(datainput)
                                            Toast.makeText(this, "Bookmark berhasil dihapus", Toast.LENGTH_SHORT)
                                            break
                                        }else if (i == bookList.size-1 && bookList[i] != dataintent.toString()){
                                            bookList.add(dataintent.toString())
                                            val datainput = Data(email,text.toString(),pass,bookList,document.data.get("rateValue") as ArrayList<Int>, document.data.get("rateName") as ArrayList<String>)
                                            db.collection("User").document(text.toString()).set(datainput)
                                            Toast.makeText(this, "Bookmark berhasil ditambahkan", Toast.LENGTH_SHORT)
                                        }
                                    }
                                }


                                /*if(count==0) {
                                    bookList.add(dataintent.toString())
                                    count = 1
                                }*/
                                val datainput = Data(email,text.toString(),pass,bookList,document.data.get("rateValue") as ArrayList<Int>, document.data.get("rateName") as ArrayList<String>)
                                db.collection("User").document(text.toString()).set(datainput)
                                break
                            }
                        }
                    }
        }

        db.collection("list museum")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data.get("Id").toString() == dataintent.toString()) {
                        namaMuseum.text = document.data.get("Id").toString()
                        lokasiMuseum.text = document.data.get("Lokasi").toString()
                        ratingMuseum.text =
                            document.data.get("Rating").toString().toDouble().toString()
                        reviewMuseum.text = document.data.get("Review").toString()
                        tipeMuseum.text = document.data.get("TipeMuseum").toString()
                        Log.d("TRanslatetetete", bahasa.toString())
                        araara.text = document.data.get("Museum Description").toString()
                        DeskripsiMuseum.text = document.data.get("Deskripsi Museum").toString()
//                        araara.add(document.data.get("Museum Description").toString())
//                        araara.add(document.data.get("Deskripsi Museum").toString())
//                        if (bahasa.toString() == "Ing"){
//                            Log.d("TRanslate", "Masuk1")
//                            DeskripsiMuseum.text = document.data.get("Museum Description").toString()
//                        }
//                        else {
//                            Log.d("TRanslate", "Masuk2")
//                            DeskripsiMuseum.text = document.data.get("Deskripsi Museum").toString()
//                        }
//                        if( con ==0 || con%2==0){
//                            DeskripsiMuseum.text = document.data.get("Deskripsi Museum").toString()
//                        }else if ( con%2==1){
//                            DeskripsiMuseum.text = document.data.get("Museum Description").toString()
//                        }
                        latitude = document.data.get("Lat").toString().toDouble()
                        longitude = document.data.get("Long").toString().toDouble()
                        Url = document.data.get("LinkGoogleMaps").toString()
                        val imageUrl = document.data.get("ImageDetailUrl").toString()
                        Log.d("kulimasu",imageUrl)
                        val storageReference = FirebaseStorage.getInstance().getReference("preview/" + document.data.get("Id").toString() + ".jpg")
                        val localFile = File.createTempFile("image", "jpg")
                        Log.d("malulu", localFile.toString())
                        storageReference.getFile(localFile)
                            .addOnSuccessListener { taskSnapshot ->
                                // Image download successful, set it as the background of the root layout
                                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                                Log.d("malulu", localFile.toString())
                                val drawable = BitmapDrawable(resources, bitmap)
                                constraint.background = drawable
                            }
                            .addOnFailureListener { exception ->
                                // Handle any errors that occur during the download.
                                Log.e("Firebase Storage", "Image download failed: $exception")
                            }
                        getLastLocation()
                        //audioUrl = document.data.get("audio url").toString()
                    }
                }
            }

        GMapMuseum.setOnClickListener {
            Log.d("hulili", Url)
            val webpage: Uri = Uri.parse(Url)
            val webIntent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(webIntent)
        }

        btnPlay.setOnClickListener(){
            onPlayButtonClick()

        }

    }
    fun onPlayButtonClick() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        } else {
            var dataintent = intent.getStringExtra("Id")
            val audioRef = storageRef.child("Audio").child(dataintent.toString()+".mp3")
            audioRef.downloadUrl.addOnSuccessListener { uri ->
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(uri.toString())
                    prepare()
                    start()
                }
            }.addOnFailureListener { exception ->
                // Handle any errors that occur during the download or playback process
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun onStopButtonClick() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }
    private fun hitungJarak(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371
        val lat1Rad = Math.toRadians(lat1)
        val lon1Rad = Math.toRadians(lon1)
        val lat2Rad = Math.toRadians(lat2)
        val lon2Rad = Math.toRadians(lon2)

        val dLat = lat2Rad - lat1Rad
        val dLon = lon2Rad - lon1Rad

        val a = sin(dLat / 2).pow(2) + cos(lat1Rad) * cos(lat2Rad) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))


        return earthRadius * c
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

    @SuppressLint("SetTextI18n")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                mFusedLocationClient.lastLocation.addOnCompleteListener(this,
                    OnCompleteListener { task: Task<Location?> ->
                        val location = task.result
                        if (location == null) {
                            requestNewLocationData()
                        } else {
                            var a = hitungJarak(location.latitude, location.longitude, latitude, longitude)
                            var b = customFormat(a)
                            jarakMuseum.text = "Jarak : " + b + " km"
                        }
                    })
            } else {
                Toast.makeText(this, "Please turn on your location...", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            // If permissions aren't available, request for permissions
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        // Initializing LocationRequest object with appropriate methods
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 5
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        // Setting LocationRequest on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest,
            mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        @SuppressLint("SetTextI18n")
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location? = locationResult.lastLocation
            var a = hitungJarak(mLastLocation!!.latitude, mLastLocation!!.longitude, latitude, longitude)
            var b = customFormat(a)
            jarakMuseum.text = "Jarak : " + b + " km"
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ), PERMISSION_ID)
    }
    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (checkPermissions()) {
            getLastLocation()
        }
    }
}