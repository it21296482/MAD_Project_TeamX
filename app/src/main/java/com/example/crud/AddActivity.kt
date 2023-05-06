package com.example.crud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var licenseNo: EditText
    private lateinit var phone: EditText
    private lateinit var hdUrl: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        name = findViewById(R.id.txtName)
        licenseNo = findViewById(R.id.txtLicenseNo)
        phone = findViewById(R.id.txtPhone)
        hdUrl = findViewById(R.id.txtimgUrl)

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
        map["licenseNo"] = licenseNo.text.toString()
        map["phone"] = phone.text.toString()
        map["hdUrl"] = hdUrl.text.toString()

        FirebaseDatabase.getInstance().reference.child("Driver").push()
            .setValue(map)
            .addOnSuccessListener {
                Toast.makeText(this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error while Insertion", Toast.LENGTH_SHORT).show()
            }
    }
}
