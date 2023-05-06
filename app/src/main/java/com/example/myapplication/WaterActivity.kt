package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class WaterActivity : AppCompatActivity() {

    private lateinit var unitsInput: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water)

        unitsInput = findViewById(R.id.unitW)
        calculateButton = findViewById(R.id.calBtnw)
        resultTextView = findViewById(R.id.priceW)

        calculateButton.setOnClickListener {
            val units = unitsInput.text.toString().toInt()

            val price = calculatePrice(units)

            resultTextView.text = getString(R.string.result_text, units, price)
        }
    }

    private fun calculatePrice(units: Int): Double {

        val price: Double = when {
            units <= 10 -> units * 250.0
            units <= 20 -> 250.0 + (units - 10) * 3.0
            units <= 30 -> 550.0 + (units - 20) * 3.5
            else -> 950.0 + (units - 30) * 4.0
        }

        return price
    }
}