package com.example.connectapi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        val title = intent.extras?.getString("title")
        val content = intent.extras?.getString("content")

        var titleView = findViewById<TextView>(R.id.textViewTitle)
        titleView.text = title

        var btnBack = findViewById<Button>(R.id.buttonBack)
        btnBack.setOnClickListener {
            super.onBackPressed();
        }
    }
}