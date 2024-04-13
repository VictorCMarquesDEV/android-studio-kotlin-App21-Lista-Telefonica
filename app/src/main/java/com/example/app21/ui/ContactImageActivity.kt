package com.example.app21.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app21.R
import com.example.app21.databinding.ActivityContactImageBinding

class ContactImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactImageBinding
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityContactImageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        i = intent

        binding.imageProfile1.setOnClickListener {
            sendID(R.drawable.profile1)
        }

        binding.imageProfile2.setOnClickListener {
            sendID(R.drawable.profile2)
        }

        binding.imageProfile3.setOnClickListener {
            sendID(R.drawable.profile3)
        }

        binding.imageProfile4.setOnClickListener {
            sendID(R.drawable.profile4)
        }

        binding.imageProfile5.setOnClickListener {
            sendID(R.drawable.profile5)
        }

        binding.imageProfile6.setOnClickListener {
            sendID(R.drawable.profile6)
        }

        binding.buttonRemove.setOnClickListener {
            sendID(R.drawable.noprofile)
        }
    }

    private fun sendID(id: Int) {
        i.putExtra("id", id)
        setResult(1, i)
        finish()
    }
}