package com.example.fx.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.fx.ChangePasswordActivity
import com.example.fx.LoginActivity
import com.example.fx.R
import com.example.fx.RegisterActivity
import com.google.firebase.auth.FirebaseAuth


class Settings_Fragment : Fragment() {

    private lateinit var change_password_settings:TextView
    private lateinit var logout_settings:TextView
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        change_password_settings = view.findViewById(R.id.change_password_settings)
        logout_settings = view.findViewById(R.id.logout_settings)

        change_password_settings.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        logout_settings.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}