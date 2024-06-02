package com.example.vechilemonitoringapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.register_vehicle)
        val btn2 = findViewById<Button>(R.id.check_health)

        btn1.setOnClickListener(){
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btn2.setOnClickListener(){
            startActivity(Intent(this, CheckHealthActivity::class.java ))
        }
    }
}