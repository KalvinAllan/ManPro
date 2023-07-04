package com.example.manpro

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    val db = FirebaseFirestore.getInstance()
    private var listMuseum = arrayListOf<Museum>()
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        val search = findViewById<EditText>(R.id.Search)
        var btnsearch = findViewById<Button>(R.id.button2)
        val filterRating = findViewById<Button>(R.id.button4)
        var sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        val filterNam = findViewById<Button>(R.id.button5)
        val profile = findViewById<ImageView>(R.id.imageView3)
        val filyer =findViewById<Button>(R.id.Filter)

        filyer.setOnClickListener {
            val b = Intent(this@MainActivity,FilterRatinggg::class.java)
            b.putExtra("usrname",text.toString())
            val c = Intent(this@MainActivity,FilterRatinggg::class.java)
            startActivity(b)
        }

        profile.setOnClickListener{
            val b = Intent(this@MainActivity,Profile::class.java)
            b.putExtra("usrname",text.toString())
            startActivity(b)
        }

        btnsearch.setOnClickListener {
                val b = Intent(this@MainActivity,SearchActivity::class.java)
                b.putExtra("Di",search.text.toString())
                startActivity(b)
        }

        filterRating.setOnClickListener{
            val b = Intent(this@MainActivity,FilterRating::class.java)
            startActivity(b)
        }
        filterNam.setOnClickListener{
            val b = Intent(this@MainActivity,FilterJarak::class.java)
            startActivity(b)
        }

        contentBased()
    }

    fun collaborativeFiltering(){
        val db = FirebaseFirestore.getInstance()
        var mapRating = hashMapOf<String, Any>()
        var mapReview = hashMapOf<String, Any>()
        var listMuseum = ArrayList<Museum>()
        db.collection("list museum").get().addOnSuccessListener{
                result -> for (document in result){
            mapRating.put(document.data.get("Id").toString(), 0)
            mapReview.put(document.data.get("Id").toString(), document.data.get("Review").toString())
        }
            db.collection("User").get().addOnSuccessListener{
                    resultt -> for (documente in resultt){
                var ratingName = documente.data.get("rateName") as ArrayList<String>
                var ratingValue = documente.data.get("rateValue") as ArrayList<Int>
                for (i in ratingValue.indices){
                    if (ratingValue[i] >= 3){
                        ratingValue[i] = 1
                    }
                    else{
                        ratingValue[i] = 0
                    }
                }
                for (i in ratingName){
                    mapReview.forEach { (key, valuee) ->
                        if (i == key) {
                            var divisor = Math.pow(10.0, (mapReview[key].toString().length - 1).toDouble()).toInt()
                            var hasil = (mapReview[key].toString().toDouble() * 0.2)  / (divisor * 10)
                            mapRating.forEach { (key, value) ->
                                if (i == key) {
                                    mapRating[key] = (value.toString().toDouble() + ratingValue[ratingName.indexOf(i)].toDouble() * 0.8 + hasil.toDouble()).toDouble()
                                }
                            }
                        }
                    }
                }
            }
                val sortedEntries = mapRating.entries.sortedByDescending { it.value.toString().toDouble()}
                val sortedMap = sortedEntries.associate { it.key to it.value.toString().toDouble() }
                sortedMap.forEach { (key, value) ->
                db.collection("list museum").get().addOnSuccessListener { resulte ->
                    for (documentt in resulte) {
                            if (listMuseum.size < 5){
                                if (key == "Blockbuster Museum" && documentt.data.get("Id") == "Blockbuster Museum") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.blockbuster_museum
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "H.O.S Tjokroaminoto" && documentt.data.get("Id") == "H.O.S Tjokroaminoto") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.hos_tjokroaminoto
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "House of Sampoerna" && documentt.data.get("Id") == "House of Sampoerna") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.house_of_sampoerna
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Bank Indonesia" && documentt.data.get("Id") == "Museum Bank Indonesia") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_bank_indonesia
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Dr. Soetomo" && documentt.data.get("Id") == "Museum Dr. Soetomo") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_dr_soetomo
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Kanker Indonesia" && documentt.data.get("Id") == "Museum Kanker Indonesia") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_kanker_indonesia
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Kesehatan Dr. Adhyatma" && documentt.data.get("Id") == "Museum Kesehatan Dr. Adhyatma") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_kesehatan_dr_adhyatma
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Olahraga Surabaya" && documentt.data.get("Id") == "Museum Olahraga Surabaya") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_olahraga_surabaya
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Pendidikan Surabaya" && documentt.data.get("Id") == "Museum Pendidikan Surabaya") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_pendidikan_surabaya
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum Sepuluh Nopember" && documentt.data.get("Id") == "Museum Sepuluh Nopember" ) {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_sepuluh_nopember
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Museum WR Soepratman" && documentt.data.get("Id") == "Museum WR Soepratman") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.museum_wr_soepratman
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key == "Siola Surabaya Museum" && documentt.data.get("Id") == "Siola Surabaya Museum") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.siola_surabaya_museum
                                    )
                                    listMuseum.add(msm)
                                }
                                else if (key.toString() == "Teknoform Museum" && documentt.data.get("Id") == "Teknoform Museum") {
                                    val msm = Museum(
                                        documentt.getString("Id").toString(),
                                        documentt.getString("Lokasi").toString(),
                                        documentt.get("Rating").toString().toDouble(),
                                        documentt.get("Review").toString().toInt(),
                                        documentt.getString("TipeMuseum").toString(),
                                        R.drawable.teknoform_museum
                                    )
                                    listMuseum.add(msm)

                                }
                            }
                        }
                        recyclerView.layoutManager = LinearLayoutManager(this)
                        recyclerView.adapter = AdapterMuseum(listMuseum)
                    }
                }
            }

        }
    }


    private fun contentBased(){
        var c1=0
        var c2=0
        var c3=0
        var c4=0
        var c5=0
        var c6=0
        var c7=0
        var c8=0
        var c9=0
        var c10=0
        var c11=0
        var c12=0
        var c13=0
        val db = FirebaseFirestore.getInstance()
        var mapTipe1 = hashMapOf<String, Any>()
        var NamaM = ArrayList<String>()
        var ValueM = ArrayList<String>()
        var book = ArrayList<String>()
        var sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        var listMuseum = ArrayList<Museum>()
        db.collection("User").get().addOnSuccessListener{
                result->
            for (i in result){
                if (i.data.get("nama") == text.toString()){
                    book = i.data.get("book") as ArrayList<String>
                    break
                }
            }
            if (book.isNullOrEmpty() || book.size == 1){
               collaborativeFiltering()
            }
            db.collection("list museum").orderBy("Rating", Query.Direction.DESCENDING).get().addOnSuccessListener{
                    resultt->
                for (document in resultt){
                    for (i in book){
                    if (i == document.data.get("Id").toString()) {
                        var tipe = document.data.get("TipeMuseum1").toString()
                        var tipe2 = document.data.get("TipeMuseum2").toString()
                        if (tipe.isNullOrEmpty()) {
                        }
                        else if (tipe2.isNullOrEmpty()){
                        } else {
                            NamaM.add(tipe)
                            NamaM.add(tipe2)
                            }
                        }
                    }
                    for (i in book){
                        if (i!=document.data.get("Id")){
                            for (z in NamaM){
                                if (document.data.get("TipeMuseum1").toString()== z.toString()){
                                    if (ValueM.isEmpty()){
                                        ValueM.add(document.data.get("Id").toString())
                                    }else if (ValueM.isNotEmpty()){
                                        for (x in ValueM){
                                            if (x.toString()== document.data.get("Id").toString()){
                                                break
                                            }else if (x.toString()!= document.data.get("Id").toString()&& ValueM.indexOf(x) == ValueM.size - 1){
                                                ValueM.add(document.data.get("Id").toString())
                                                Log.d("Hasil sort value : ", ValueM.toString())
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    val sortedEntries = mapTipe1.entries.sortedByDescending { it.value.toString().toInt() }
                    Log.d("hasil sorted entries : ", sortedEntries.toString())
                    val sortedMap = sortedEntries.associate { it.key to it.value.toString().toDouble() }
                    Log.d("hasil sorted map : ", sortedMap.toString())
                    for (i in ValueM){
                    if (listMuseum.size < 5){
                        if (document.data.get("Id").toString()=="Blockbuster Museum" && c1==0 && document.data.get("Id").toString() == i.toString()){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.blockbuster_museum)
                            listMuseum.add(msm)
                            c1=c1+1
                        }
                        if (document.data.get("Id").toString()=="H.O.S Tjokroaminoto"&& c2==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.hos_tjokroaminoto)
                            listMuseum.add(msm)
                            c2=c2+1
                        }
                        if (document.data.get("Id").toString()=="House of Sampoerna"&& c3==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.house_of_sampoerna)
                            listMuseum.add(msm)
                            c3=c3+1
                        }
                        if (document.data.get("Id").toString()=="Museum Bank Indonesia"&& c4==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_bank_indonesia)
                            listMuseum.add(msm)
                            c4=c4+1
                        }
                        if (document.data.get("Id").toString()=="Museum Dr. Soetomo"&& c5==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_dr_soetomo)
                            listMuseum.add(msm)
                            c5=c5+1
                        }
                        if (document.data.get("Id").toString()=="Museum Kanker Indonesia" && c6==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_kanker_indonesia)
                            listMuseum.add(msm)
                            c6=c6+1
                        }
                        if (document.data.get("Id").toString()=="Museum Kesehatan Dr. Adhyatma"&& c7==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_kesehatan_dr_adhyatma)
                            listMuseum.add(msm)
                            c7=c7+1
                        }
                        if (document.data.get("Id").toString()=="Museum Olahraga Surabaya"&& c8==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_olahraga_surabaya)
                            listMuseum.add(msm)
                            c8=c8+1
                        }
                        if (document.data.get("Id").toString()=="Museum Pendidikan Surabaya" && c9==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_pendidikan_surabaya)
                            listMuseum.add(msm)
                            c9=c9+1
                        }
                        if (document.data.get("Id").toString()=="Museum Sepuluh Nopember"&& c10==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_sepuluh_nopember)
                            listMuseum.add(msm)
                            c10=c10+1
                        }
                        if (document.data.get("Id").toString()=="Museum WR Soepratman"&& c11==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.museum_wr_soepratman)
                            listMuseum.add(msm)
                            c11=c11+1
                        }
                        if (document.data.get("Id").toString()=="Siola Surabaya Museum"&& c12==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.siola_surabaya_museum)
                            listMuseum.add(msm)
                            c12=c12+1
                        }
                        if (document.data.get("Id").toString()=="Teknoform Museum"&& c13==0 && document.data.get("Id").toString() == i){
                            val msm = Museum(document.getString("Id").toString(),document.getString("Lokasi").toString(),document.get("Rating").toString().toDouble(),document.get("Review").toString().toInt(),document.getString("TipeMuseum").toString(),R.drawable.teknoform_museum)
                            listMuseum.add(msm)
                            c13=c13+1
                            }
                        }
                    }

                }
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = AdapterMuseum(listMuseum)
            }

            Log.d("Listo museum hasil :", listMuseum.toString())
        }

    }

    private fun SiapkanData(){
        db.collection("list museum")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
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