package com.example.manpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class ChangeEmail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        val dataintent = intent.getStringExtra("NamaProfile")
        val db = FirebaseFirestore.getInstance()
        var tb = findViewById<EditText>(R.id.Passwordchng)
        val tb2 = findViewById<EditText>(R.id.Password2chng)
        val change =findViewById<Button>(R.id.buttonchng2)
        var map = hashMapOf<String, Any>()

        change.setOnClickListener {
            db.collection("User").get().addOnSuccessListener { res->
                for (doc in res){
                    if (doc.data.get("nama").toString()==dataintent.toString()){
                        if (doc.data.get("email").toString()==tb.text.toString()){
                            map.set("email",tb2.text.toString())
                            db.collection("User").document(dataintent.toString())
                                .set(map , SetOptions.merge())
                        }
                    }
                }
            }
            val intent = Intent(this@ChangeEmail,Profile::class.java)
            intent.putExtra("usrname",dataintent.toString())
            startActivity(intent)
        }
    }
}