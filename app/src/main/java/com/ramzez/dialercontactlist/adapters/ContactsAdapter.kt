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
import com.ramzez.dialercontactlist.R
import com.ramzez.dialercontactlist.models.Contact


class ContactAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    // - Определяет, какие View будут использоваться для отображения элементов списка.
    // т.е. создаем внутренний класс ContactViewHolder, который будет использоваться для хранения ссылок на
    // элементы макета и упрощения доступа к ним в onBindViewHolder(). Обратите внимание, что мы используем
    // метод findViewById() для поиска элементов макета по их идентификаторам, заданным в файле contact_item.xml.

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.contact_name)
        val phoneTextView: TextView = itemView.findViewById(R.id.contact_phone)
        val callButton: Button = itemView.findViewById(R.id.call_button)

        fun bind(contact: Contact, context: Context) {
            nameTextView.text = contact.name
            phoneTextView.text = contact.phone
            callButton.setOnClickListener {
                val phoneNumber = contact.phone
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                context.startActivity(intent)
            }
        }
    }

    // onCreateViewHolder() создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(itemView)
    }

    // onBindViewHolder() привязывает объект ViewHolder к объекту модели и устанавливает необходимые значения
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.phoneTextView.text = contact.phone
        // Назначаем обработчик клика на кнопку
        holder.callButton.setOnClickListener {
            val phoneNumber = "tel:${contact.phone}"
            val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber))
            holder.itemView.context.startActivity(dialIntent)
        }
    }

    // getItemCount() возвращает количество элементов в списке контактов
    override fun getItemCount() = contacts.size



}