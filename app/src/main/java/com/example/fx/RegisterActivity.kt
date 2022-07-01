package com.example.fx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var Email_Register: EditText
    private lateinit var Password_Register: EditText
    private lateinit var Button_Signup: Button
    private lateinit var Go_back_Tv: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Email_Register = findViewById(R.id.Email_Register)
        Password_Register = findViewById(R.id.Password_Register)
        Button_Signup = findViewById(R.id.Button_Signup)
        Go_back_Tv = findViewById(R.id.Go_back_Tv)

        auth = Firebase.auth

        Button_Signup.setOnClickListener {
            SignUpUser()
        }

        Go_back_Tv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun SignUpUser() {
        val email = Email_Register.text.toString()
        val password = Password_Register.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}