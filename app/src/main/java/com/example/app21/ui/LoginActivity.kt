package com.example.app21.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app21.R
import com.example.app21.database.DBHelper
import com.example.app21.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = DBHelper(this)
        sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        if (username != null) {
            if(username.isNotEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        binding.buttonLogin.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editSenha.text.toString()
            val logged = binding.checkLogged.isChecked

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val res = db.loginUser(username, password)
                if (res) {
                    if (logged) {
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("username", username)
                        editor.apply()
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.editUsername.setText("")
                    binding.editSenha.setText("")
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.please_insert_all_required_fields,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.textSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}