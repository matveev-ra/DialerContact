package com.ramzez.dialercontactlist.models

data class Contact(var name: String, var phone: String) {

    fun editName(newName: String) {
        name = newName
    }

    fun editPhone(newPhone: String) {
        phone = newPhone
    }
}
