package com.example.jsondemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etTags = findViewById<EditText>(R.id.etTags)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        val testProduct = Product(
            "Программирование на Kotlin",
            1500.0,
            listOf("учебник", "программирование", "android")
        )
        val testJson = Gson().toJson(testProduct)
        Log.d("MyApp", "Тестовый JSON: $testJson")

        btnConvert.setOnClickListener {
            val name = etName.text.toString()
            val priceText = etPrice.text.toString()
            val price = if (priceText.isEmpty()) 0.0 else priceText.toDouble()

            val tagsText = etTags.text.toString()
            val tagsList = tagsText.split(",")

            val product = Product(name, price, tagsList)

            val json = Gson().toJson(product)
            Log.d("MyApp", "Получили JSON: $json")

            val restoredProduct = Gson().fromJson(json, Product::class.java)

            tvResult.text = "JSON: " + json + "\n\nНазвание товара после десериализации: " + restoredProduct.name

            Log.d("MyApp", "Название после десериализации: ${restoredProduct.name}")
        }
    }
}