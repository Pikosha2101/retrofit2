package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.tv)
        val et = findViewById<EditText>(R.id.et)
        val bt = findViewById<Button>(R.id.button)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi = retrofit.create(JsonAPI::class.java)
        bt.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val id = et.text.toString().toInt()
                val product = productApi.getProductByID(id)
                runOnUiThread{
                    var str = "Id: " + product.id
                    str += "\nBrand: " + product.brand
                    str += "\nTitle: " + product.title
                    tv.text = str
                }
            }
        }
    }
}