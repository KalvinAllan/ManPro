package com.example.manpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TermsOfService : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_service)
        val dataintent = intent.getStringExtra("NamaProfile")
        val btn = findViewById<Button>(R.id.button16)

        btn.setOnClickListener {
            val intent = Intent(this@TermsOfService,Profile::class.java)
            intent.putExtra("usrname",dataintent.toString())
            startActivity(intent)
        }
    }
}