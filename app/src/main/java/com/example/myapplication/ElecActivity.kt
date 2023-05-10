package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.databinding.ActivityElecBinding

class ElecActivity : AppCompatActivity() {

    private lateinit var unitsInput: EditText
    private lateinit var resultTextView: TextView
    private lateinit var binding: ActivityElecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        unitsInput = findViewById(R.id.unitW)
        resultTextView = findViewById(R.id.priceW)

        binding.calBtnE.setOnClickListener {
            val units = unitsInput.text.toString().toInt()

            val price = calculatePrice(units)

            resultTextView.text = String.format("Rs.%.2f", price)

        }

        binding.watBtn.setOnClickListener {
            val intent = Intent(this, WaterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun calculatePrice(units: Int): Double {

        val price: Double = when {
            units <= 10 -> units * 300.0
            units <= 20 -> 330.0 + (units - 10) * 3.0
            units <= 30 -> 350.0 + (units - 20) * 3.5
            else -> 1110.0 + (units - 30) * 4.0
        }

        return price
    }
}