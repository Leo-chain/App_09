package com.example.app_09

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        val etSys = findViewById<EditText>(R.id.etSys)
        val etDia = findViewById<EditText>(R.id.etDia)
        val etHr = findViewById<EditText>(R.id.etHr)
        val btn_send = findViewById<Button>(R.id.btnSubmit)

        btn_send.setOnClickListener {
            var bundle = Bundle()
            var data = etSys.text.toString() + etDia.text.toString() +etHr.text.toString()
            bundle.putString("data",data)
            var mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra("key",bundle)
            startActivity(mainIntent)
        }
    }
}