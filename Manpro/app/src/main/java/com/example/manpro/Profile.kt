package com.example.manpro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val sharedPref =  getSharedPreferences("Login", Context.MODE_PRIVATE)
        val text = sharedPref.getString("usernameP", "")
        val dataintent = intent.getStringExtra("usrname")
        val txt = findViewById<TextView>(R.id.username)
        val btn = findViewById<Button>(R.id.button3)
        val cp = findViewById<Button>(R.id.button7)
        val ce = findViewById<Button>(R.id.button6)
        val mFragment = supportFragmentManager

        val btnlogOut = findViewById<Button>(R.id.button9)
        val btnTOS = findViewById<Button>(R.id.button8)

        txt.setText(dataintent.toString())
        ce.setOnClickListener {
            val intent = Intent(this@Profile,ChangeEmail::class.java)
            intent.putExtra("NamaProfile",dataintent.toString())
            startActivity(intent)
        }
        cp.setOnClickListener {
            val intent = Intent(this@Profile,ChangePass::class.java)
            intent.putExtra("NamaProfile",dataintent.toString())
            startActivity(intent)
        }

        btn.setOnClickListener {
            val intent = Intent(this@Profile,Bookmark::class.java)
            intent.putExtra("NamaProfile",dataintent.toString())
            startActivity(intent)
        }
        btnTOS.setOnClickListener {
            val intent = Intent(this@Profile,TermsOfService::class.java)
            intent.putExtra("NamaProfile",dataintent.toString())
            startActivity(intent)
        }
        btnlogOut.setOnClickListener {
            with(sharedPref.edit()) {
               clear()
                apply()
                val intent = Intent(this@Profile,Opening::class.java)
                startActivity(intent)
            }

        }
    }
}