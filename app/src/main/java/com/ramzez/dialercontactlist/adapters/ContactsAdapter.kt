package com.ramzez.dialercontactlist.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
        private val callButton: Button = itemView.findViewById(R.id.call_button)
        val nameEditText: EditText = itemView.findViewById(R.id.edit_name)
        val phoneEditText: EditText = itemView.findViewById(R.id.edit_phone)
        private val editButton: Button = itemView.findViewById(R.id.edit_button)
        private val saveButton: Button = itemView.findViewById(R.id.save_button)
        init {
            editButton.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentContact = contactList[position]
                    // Логика кнопки "Редактировать"
                    if (editButton.text == "Редактировать") {
                        nameTextView.visibility = View.GONE
                        phoneTextView.visibility = View.GONE
                        nameEditText.visibility = View.VISIBLE
                        phoneEditText.visibility = View.VISIBLE
                        editButton.text = "Сохранить"
                        nameEditText.setText(currentContact.name)
                        phoneEditText.setText(currentContact.phone)
                    } else {
                        val newName = nameEditText.text.toString()
                        val newPhone = phoneEditText.text.toString()
                        currentContact.name = newName
                        currentContact.phone = newPhone

                        nameTextView.text = newName
                        phoneTextView.text = newPhone

                        nameTextView.visibility = View.VISIBLE
                        phoneTextView.visibility = View.VISIBLE
                        nameEditText.visibility = View.GONE
                        phoneEditText.visibility = View.GONE
                        editButton.text = "Редактировать"
                    }
                }
            }


            editButton.setOnClickListener {
                // Обработка нажатия кнопки "Редактировать"
                nameTextView.visibility = View.GONE
                phoneTextView.visibility = View.GONE
                editButton.visibility = View.GONE
                callButton.visibility = View.GONE
                saveButton.visibility = View.VISIBLE
                nameEditText.visibility = View.VISIBLE
                phoneEditText.visibility = View.VISIBLE

                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    lastClickedPosition = position
                }
            }

            saveButton.setOnClickListener {
                // Обработка нажатия кнопки "Сохранить"
                nameTextView.visibility = View.VISIBLE
                phoneTextView.visibility = View.VISIBLE
                editButton.visibility = View.VISIBLE
                callButton.visibility = View.VISIBLE
                saveButton.visibility = View.GONE
                nameEditText.visibility = View.GONE
                phoneEditText.visibility = View.GONE

                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentContact = contactList[position]
                    val newName = nameEditText.text.toString()
                    val newPhone = phoneEditText.text.toString()
                    currentContact.editName(newName)
                    currentContact.editPhone(newPhone)
                    notifyItemChanged(position)
                }
            }




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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactList[position]

        holder.nameTextView.text = currentContact.name
        holder.phoneTextView.text = currentContact.phone

        // Установка значений в EditText поля при редактировании
        holder.nameEditText.setText(currentContact.name)
        holder.phoneEditText.setText(currentContact.phone)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}