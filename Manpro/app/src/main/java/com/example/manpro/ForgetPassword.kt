package com.example.manpro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ForgetPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        val btn = findViewById<Button>(R.id.buttonver)
        val email = findViewById<EditText>(R.id.Emailfrgtpswd)
        val db = FirebaseFirestore.getInstance()


        btn.setOnClickListener {
            db.collection("User")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        if (document.get("nama").toString() ==email.text.toString()||document.get("email").toString()==email.text.toString()){
                                    val b = Intent(this@ForgetPassword,ForgetPassword2::class.java)
                                    b.putExtra("pwd",email.text.toString())
                                    startActivity(b)
                            }
                        else{
                            Toast.makeText(this, "Username/Email tidak sesuai", Toast.LENGTH_SHORT)
                        }
                        }

                    }

                }
    }
}