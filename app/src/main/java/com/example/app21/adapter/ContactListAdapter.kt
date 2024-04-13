package com.example.app21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app21.R
import com.example.app21.adapter.listener.ContactOnClickListener
import com.example.app21.adapter.viewholder.ContactViewHolder
import com.example.app21.model.ContactModel

class ContactListAdapter(
    private val contactList: List<ContactModel>,
    private val contactOnClickListener: ContactOnClickListener
) : RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList[position]

        holder.textName.text = contact.name
        if (contact.imageId > 0) {
            holder.image.setImageResource(contact.imageId)
        } else {
            holder.image.setImageResource(R.drawable.noprofile)
        }

        holder.itemView.setOnClickListener {
            contactOnClickListener.clickListener(contact)
        }
    }
}