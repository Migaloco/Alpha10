package com.example.alpha10

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var mSignin: Button? = null
    private var mLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSignin = findViewById(R.id.signin) as Button
        mLogin = findViewById(R.id.login) as Button

        mLogin!!.setOnClickListener { goToLogin() }

        mSignin!!.setOnClickListener { goToSignIn() }
    }

    protected fun goToLogin() {

        val i = Intent(this, LogIn::class.java)
        startActivity(i)
    }

    protected fun goToSignIn() {

        val i = Intent(this, SignIn::class.java)
        startActivity(i)
    }
}
