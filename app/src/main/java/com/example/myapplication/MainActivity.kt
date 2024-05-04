package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val conversionOptions =
            listOf("Celsius to Fahrenheit", "Meters to Feet", "Kilograms to Pounds")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.conversionSpinner.adapter = adapter

        binding.convertButton.setOnClickListener {
            val inputValue = binding.inputText.text.toString().toDouble()
            val conversionType = binding.conversionSpinner.selectedItem.toString()

            val result = when (conversionType) {
                "Celsius to Fahrenheit" -> celsiusToFahrenheit(inputValue)
                "Meters to Feet" -> metersToFeet(inputValue)
                "Kilograms to Pounds" -> kilogramsToPounds(inputValue)
                else -> 0.0
            }

            binding.resultText.text = "%.2f".format(result)

            // Display the "Conversion Result" TextView
            binding.resultLabel.visibility = android.view.View.VISIBLE

            // Display the unit labels based on the selected conversion type
            when (conversionType) {
                "Celsius to Fahrenheit" -> {
                    binding.fahrenheitLabel.visibility = android.view.View.VISIBLE
                    binding.feetLabel.visibility = android.view.View.GONE
                    binding.poundsLabel.visibility = android.view.View.GONE
                }

                "Meters to Feet" -> {
                    binding.fahrenheitLabel.visibility = android.view.View.GONE
                    binding.feetLabel.visibility = android.view.View.VISIBLE
                    binding.poundsLabel.visibility = android.view.View.GONE
                }

                "Kilograms to Pounds" -> {
                    binding.fahrenheitLabel.visibility = android.view.View.GONE
                    binding.feetLabel.visibility = android.view.View.GONE
                    binding.poundsLabel.visibility = android.view.View.VISIBLE
                }

                else -> {
                    binding.fahrenheitLabel.visibility = android.view.View.GONE
                    binding.feetLabel.visibility = android.view.View.GONE
                    binding.poundsLabel.visibility = android.view.View.GONE
                }
            }
        }
    }

    private fun celsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    private fun metersToFeet(meters: Double): Double {
        return meters * 3.28084
    }

    private fun kilogramsToPounds(kilograms: Double): Double {
        return kilograms * 2.20462
    }
}