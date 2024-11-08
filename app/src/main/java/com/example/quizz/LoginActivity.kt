package com.example.quizz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizz.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { Firebase.auth.createUserWithEmailAndPassword(binding.email.text.toString(),
            binding.password.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this, "User created!!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,QuizActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, "User not created!!", Toast.LENGTH_SHORT).show()

                }
        }

        }

    }
}