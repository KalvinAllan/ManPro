package com.example.manpro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.Namemsk)
        val Pass = findViewById<EditText>(R.id.Passwordmsk)
        val btn = findViewById<Button>(R.id.buttonmsk)
        val frgt = findViewById<TextView>(R.id.textViewfrgtpwd)
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val db = FirebaseFirestore.getInstance()

        val masuk = findViewById<TextView>(R.id.textViewdftr)


        masuk.setOnClickListener{
            val b = Intent(this@Login,Register::class.java)
            startActivity(b)
        }

        frgt.setOnClickListener{
            val b = Intent(this@Login,ForgetPassword::class.java)
            startActivity(b)
        }

        btn.setOnClickListener {
            db.collection("User")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        if (username.text.toString()=="" || Pass.text.toString()==""){
                            Toast.makeText(this, "Username/email tidak ada", Toast.LENGTH_SHORT)
                        }
                        if (document.get("nama").toString() ==username.text.toString()||document.get("email").toString()==username.text.toString()){
                            if (document.get("pass").toString()==Pass.text.toString()){
                                with(sharedPref.edit()) {
                                    putString("usernameP", username.text.toString())
                                    apply()
                                val intent = Intent(this@Login,MainActivity::class.java)
                                startActivity(intent)
                                }
                            }
                            else{
                                Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT)
                            }
                        }
                    }
                }
        }

    }
}