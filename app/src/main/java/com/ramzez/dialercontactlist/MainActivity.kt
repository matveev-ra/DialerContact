package com.ramzez.dialercontactlist

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramzez.dialercontactlist.adapters.ContactAdapter
import com.ramzez.dialercontactlist.models.Contact
import android.Manifest





class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter
    private val contactList = mutableListOf(
        Contact("Иванов Иван", "+7 (999) 123-45-67"),
        Contact("Петров Петр", "+7 (999) 765-43-21"),
        Contact("Сидоров Сидор", "+7 (999) 456-78-90")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.contact_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactAdapter(contactList)
        recyclerView.adapter = adapter
    }

    fun makeCall(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber))
        startActivity(dialIntent)
    }

    fun onCallButtonClicked(view: View) {
        val position = recyclerView.getChildAdapterPosition(view)
        val currentContact = contactList[position]
        val phoneNumber = "tel:${currentContact.phone}"

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            makeCall(phoneNumber)
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val position = adapter.lastClickedPosition
                val phoneNumber = "tel:${contactList[position].phone}"
                makeCall(phoneNumber)
            } else {
                Toast.makeText(
                    this,
                    "Разрешение на звонок не было предоставлено",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private const val CALL_PERMISSION_REQUEST_CODE = 123
    }


}