package com.ramzez.dialercontactlist.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ramzez.dialercontactlist.MainActivity
import com.ramzez.dialercontactlist.R
import com.ramzez.dialercontactlist.models.Contact


class ContactAdapter(private val contactList: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var lastClickedPosition = RecyclerView.NO_POSITION

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.contact_name)
        val phoneTextView: TextView = itemView.findViewById(R.id.contact_phone)
        val callButton: Button = itemView.findViewById(R.id.call_button)

        init {
            callButton.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    lastClickedPosition = position
                    (itemView.context as MainActivity).onCallButtonClicked(itemView)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactList[position]

        holder.nameTextView.text = currentContact.name
        holder.phoneTextView.text = currentContact.phone
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}