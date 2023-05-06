package com.example.reviews

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reviews.R
import com.google.firebase.database.FirebaseDatabase



class AddActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var description: EditText
    private lateinit var imgUrl: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        name = findViewById(R.id.txtName)
        email = findViewById(R.id.txtEmail)
        description = findViewById(R.id.txtDescription)
        imgUrl = findViewById(R.id.txtimgUrl)

        btnAdd = findViewById(R.id.btnAdd)
        btnBack = findViewById(R.id.btnBack)

        btnAdd.setOnClickListener {
            insertData()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun insertData() {
        val map = HashMap<String, Any>()
        map["name"] = name.text.toString()
        map["description"] = description.text.toString()
        map["email"] = email.text.toString()
        map["imgUrl"] = imgUrl.text.toString()

        FirebaseDatabase.getInstance().reference.child("Reviews").push()
            .setValue(map)
            .addOnSuccessListener {
                Toast.makeText(this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error while Insertion", Toast.LENGTH_SHORT).show()
            }
    }
}
