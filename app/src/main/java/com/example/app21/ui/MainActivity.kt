package com.example.app21.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app21.R
import com.example.app21.adapter.ContactListAdapter
import com.example.app21.adapter.listener.ContactOnClickListener
import com.example.app21.database.DBHelper
import com.example.app21.databinding.ActivityMainBinding
import com.example.app21.model.ContactModel

// App21: Lista Telefônica

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactList: List<ContactModel>

    // private lateinit var adapter: ArrayAdapter<ContactModel>
    private lateinit var adapter: ContactListAdapter
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var db: DBHelper
    private var ascDesc: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences("login", MODE_PRIVATE)

        binding.recyclerContacts.layoutManager = LinearLayoutManager(applicationContext)
        loadList()

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        /*
        binding.listContacts.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(applicationContext, ContactDetailActivity::class.java)
            intent.putExtra("id", contactList[position].id)
            result.launch(intent)
        }
        */

        binding.buttonAdd.setOnClickListener {
            result.launch(Intent(applicationContext, NewContactActivity::class.java))
        }

        binding.buttonOrder.setOnClickListener {
            if (ascDesc) {
                binding.buttonOrder.setImageResource(R.drawable.baseline_keyboard_double_arrow_up_24)
            } else {
                binding.buttonOrder.setImageResource(R.drawable.baseline_keyboard_double_arrow_down_24)
            }
            ascDesc = !ascDesc
            contactList = contactList.reversed()
            placeAdapter()
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                loadList()
            } else if (it.data != null && it.resultCode == 0) {
                Toast.makeText(applicationContext, "Operation Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun placeAdapter() {
        adapter = ContactListAdapter(contactList, ContactOnClickListener { contact ->
            val intent = Intent(applicationContext, ContactDetailActivity::class.java)
            intent.putExtra("id", contact.id)
            result.launch(intent)
        })
        binding.recyclerContacts.adapter = adapter
    }

    private fun loadList() {
        /*
        contactList = db.getAllContact()
        adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_list_item_1,
            contactList
        )
        binding.listContacts.adapter = adapter
         */

        contactList = db.getAllContact().sortedWith(compareBy { it.name })
        placeAdapter()

    }
}