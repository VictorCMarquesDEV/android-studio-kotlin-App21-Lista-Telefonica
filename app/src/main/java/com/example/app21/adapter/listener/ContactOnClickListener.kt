package com.example.app21.adapter.listener

import com.example.app21.model.ContactModel

class ContactOnClickListener(val clickListener: (contact: ContactModel) -> Unit) {

    fun onClick(contact: ContactModel) = clickListener

}