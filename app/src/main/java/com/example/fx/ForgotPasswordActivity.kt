package com.example.fx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var Email_Forgot_password: EditText
    private lateinit var Button_Submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        Email_Forgot_password = findViewById(R.id.Email_Forgot_password)
        Button_Submit = findViewById(R.id.Button_Submit)

        Button_Submit.setOnClickListener {
            val email = Email_Forgot_password.text.toString()

            if (email.isBlank()) {
                Toast.makeText(this, "Email can't be blank", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful){ Toast.makeText(this@ForgotPasswordActivity, "check your email", Toast.LENGTH_SHORT).show()
                            finish() }
                        else{Toast.makeText(this@ForgotPasswordActivity, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()}
                    }
            }
        }

    }
}