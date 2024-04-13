package com.example.app21.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app21.R

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image: ImageView = view.findViewById(R.id.image_Contact)
    val textName: TextView = view.findViewById(R.id.text_Contact_Name)
}