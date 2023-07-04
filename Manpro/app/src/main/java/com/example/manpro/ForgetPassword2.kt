package com.example.manpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class ForgetPassword2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password2)

        val Pass = findViewById<EditText>(R.id.Passwordfrgt)
        val confPass = findViewById<EditText>(R.id.Password2frgt)
        val btn = findViewById<Button>(R.id.buttonfrgt2)
        val db = FirebaseFirestore.getInstance()
        val dataintent = intent.getStringExtra("pwd")


        btn.setOnClickListener {
            db.collection("User")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result){
                        if (confPass.text.toString()==Pass.text.toString()){
                            if (document.get("nama").toString() == dataintent.toString()||document.get("email").toString()==dataintent.toString()){
                                    val dataInput = Data(document.get("nama").toString(), document.get("email").toString(), Pass.text.toString(),
                                        document.get("book") as ArrayList<String>,
                                        document.get("ratevalue") as ArrayList<Int>,
                                        document.get("ratename") as ArrayList<String>
                                    )
                                    db.collection("User").document(document.get("nama").toString()).set(dataInput)
                                    val intent = Intent(this@ForgetPassword2,Login::class.java)
                                    startActivity(intent)
                                }
                                }
                        else{
                         Toast.makeText(this, "Password and confirm Password tidak sesuai", Toast.LENGTH_SHORT)
                        }
                            }


                    }

                }
    }
}