package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bilCal.setOnClickListener {
            val intent = Intent(this, WaterActivity::class.java)
            startActivity(intent)
        }


        binding.fuelBtn.setOnClickListener {
            val intent = Intent(this, FuelHomeActivity::class.java)
            startActivity(intent)
        }
    }
}