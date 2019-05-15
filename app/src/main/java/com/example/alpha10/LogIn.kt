package com.example.alpha10

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_log_in.*

class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        log_in_button.setOnClickListener {

            val intent = Intent(this, ProfileMain::class.java)
            startActivity(intent)
        }
    }
}
