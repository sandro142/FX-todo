package com.example.fx

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var Current_password: EditText
    private lateinit var New_password: EditText
    private lateinit var Change_password_button: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        Current_password = findViewById(R.id.Current_password)
        New_password = findViewById(R.id.New_password)
        Change_password_button = findViewById(R.id.Change_password_button)
        auth = FirebaseAuth.getInstance()

        Change_password_button.setOnClickListener {
            ChangePassword()
        }

    }

    private fun ChangePassword() {
        val current = Current_password.text.toString()
        val new = New_password.text.toString()

        if (current.isBlank() || new.isBlank()) {
            Toast.makeText(this, "fill out all blanks", Toast.LENGTH_SHORT).show()
        }
        else{
            val user = auth.currentUser!!
            val credential = EmailAuthProvider
                .getCredential(user.email!!, Current_password.text.toString())
            user.reauthenticate(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        user!!.updatePassword(New_password.text.toString())
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
                                    auth.signOut()
                                    val intent = Intent(this, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                    } else {
                        Toast.makeText(this, "Changing password Failed!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}