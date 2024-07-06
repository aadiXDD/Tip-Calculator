package com.example.tipcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculate.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){
        // Get the decimal value from the cost of service text field
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDouble()
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.Option_best -> 0.20
            R.id.Option_avg -> 0.15
            else -> 0.10
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpOption.isChecked
        if(roundUp){
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}