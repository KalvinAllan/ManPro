package com.example.manpro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val Email = findViewById<EditText>(R.id.Email)
        val Username = findViewById<EditText>(R.id.Name)
        val Pass = findViewById<EditText>(R.id.Password)
        val ConfPass = findViewById<EditText>(R.id.Password2)
        val Regis = findViewById<Button>(R.id.buttondftr)
        val db = FirebaseFirestore.getInstance()
        val Array = arrayListOf<String>()
        val Array1 = arrayListOf<Int>()
        val Array2 = arrayListOf<String>()
        val masuk = findViewById<TextView>(R.id.textViewmsk)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)


        masuk.setOnClickListener {
            Log.d("masuk", Pass.toString())
            Log.d("masuk", ConfPass.toString())
            print(ConfPass.toString())
            print(Pass.toString())
            val b = Intent(this@Register, Login::class.java)
            startActivity(b)
        }

        Regis.setOnClickListener {
            Log.d("masuk", Pass.text.toString())
            Log.d("masuk", ConfPass.text.toString())
            print(ConfPass.toString())
            print(Pass.toString())
            if (Pass.text.toString() == ConfPass.text.toString()) {
                var datainput = Data(
                    Email.text.toString(),
                    Username.text.toString(),
                    Pass.text.toString(),
                    Array,
                    Array1,
                    Array2
                )
                db.collection("User").document(Username.text.toString()).set(datainput)
                val b = Intent(this@Register, Login::class.java)
                startActivity(b)
            }
        }
    }
}