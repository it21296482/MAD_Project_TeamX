package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.example.myapplication.databinding.ActivityFuelHomeBinding


class FuelHomeActivity : AppCompatActivity() {

    private lateinit var binding : FuelHomeActivity
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_home)

        userRecyclerView = findViewById(R.id.fuelList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<User>()
        getUserData()

        binding.addPass.setOnClickListener {
            val intent = Intent(this, WaterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("User")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(User::class.java)
                        userArrayList.add(user!!)
                    }

                    userRecyclerView.adapter = MyAdapter(userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }
}