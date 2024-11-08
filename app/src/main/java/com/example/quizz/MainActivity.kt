package com.example.quizz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       Handler(Looper.getMainLooper()).postDelayed(3000){
           if (Firebase.auth.currentUser!=null){
               val intent = Intent(this, QuizActivity::class.java)
               startActivity(intent)
               finish()
           }
           else{
               val intent = Intent(this, LoginActivity::class.java)
               intent.putExtra("MODE","SIGN UP")
               startActivity(intent)

           }
       }
    }
}