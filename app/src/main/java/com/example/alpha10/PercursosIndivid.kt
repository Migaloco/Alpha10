package com.example.alpha10

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_percurso_categoria.*
import kotlinx.android.synthetic.main.activity_percursos_individ.*

class PercursosIndivid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percursos_individ)

        val bundle = intent.extras
        val name = bundle.getString("name")

        percursos_individ_title.text = name
    }
}
