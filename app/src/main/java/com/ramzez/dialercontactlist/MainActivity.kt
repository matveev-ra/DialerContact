package com.ramzez.dialercontactlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramzez.dialercontactlist.adapters.ContactAdapter
import com.ramzez.dialercontactlist.models.Contact






class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1.Получаем ссылку на RecyclerView из макета активности с помощью метода findViewById().
        // Затем назначаем менеджер макета, в данном случае LinearLayoutManager,
        // который отображает элементы списка вертикально. Для этого вызываем метод setLayoutManager() у RecyclerView,
        // передавая ему новый объект LinearLayoutManager, созданный через конструктор:
        val recyclerView = findViewById<RecyclerView>(R.id.contact_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //2.Создаем список контактов и экземпляр адаптера. Для этого создаем объект List<Contact>,
        // в котором содержатся объекты Contact, представляющие каждый контакт.
        // Затем передаем этот список в конструктор ContactAdapter, который будет
        // использоваться для заполнения RecyclerView.
        val contacts = listOf(
            Contact("Roman", "+77058140157"),
            Contact("Jane Doe", "456-789-0123"),
            Contact("Bob Smith", "789-012-3456")
        )
        val adapter = ContactAdapter(contacts)

        //Назначаем адаптер RecyclerView. Для этого вызываем метод setAdapter() у RecyclerView,
        // передавая ему экземпляр адаптера:
        recyclerView.adapter = adapter


    }
}