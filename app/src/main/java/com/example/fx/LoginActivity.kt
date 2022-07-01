package com.example.fx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var Email_Login: EditText
    private lateinit var Password_Login: EditText
    private lateinit var Forgot_password_Tv: TextView
    private lateinit var Button_Login: Button
    private lateinit var New_account_Tv: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Email_Login = findViewById(R.id.Email_Login)
        Password_Login = findViewById(R.id.Password_Login)
        Forgot_password_Tv = findViewById(R.id.Forgot_password_Tv)
        New_account_Tv = findViewById(R.id.New_account_Tv)
        Button_Login = findViewById(R.id.Button_Login)

        auth = FirebaseAuth.getInstance()

        Button_Login.setOnClickListener {
            Login()
        }

        Forgot_password_Tv.setOnClickListener{
            Forgot_password()
        }

        New_account_Tv.setOnClickListener {
            New_account()
        }
    }

    private fun New_account() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun Forgot_password() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun Login() {
        val email = Email_Login.text.toString()
        val password = Password_Login.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
}