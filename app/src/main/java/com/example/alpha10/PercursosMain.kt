package com.example.alpha10

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_percursos_main.*
import kotlinx.android.synthetic.main.activity_percursos_main.view.*
import kotlinx.android.synthetic.main.percursos_main_recycler_view.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class PercursosMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_percursos_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val listOfCategories = ArrayList<ArrayList<String>>()

        val listFavourites = ArrayList<String>()
        listFavourites.add("Favourites")
        listFavourites.add("PercFavourites1")
        listFavourites.add("PercFavourites2")
        listFavourites.add("PercFavourites3")
        listFavourites.add("PercFavourites4")
        listFavourites.add("PercFavourites5")
        listFavourites.add("PercFavourites6")
        listFavourites.add("PercFavourites7")
        listFavourites.add("PercFavourites8")
        listFavourites.add("PercFavourites9")
        listFavourites.add("PercFavourites10")
        listFavourites.add("PercFavourites11")
        listFavourites.add("PercFavourites12")


        val listPopular = ArrayList<String>()
        listPopular.add("Popular")
        listPopular.add("PercPopular1")
        listPopular.add("PercPopular2")
        listPopular.add("PercPopular3")

        val listNew = ArrayList<String>()
        listNew.add("New")
        listNew.add("PercNew1")
        listNew.add("PercNew2")
        listNew.add("PercNew3")

        val listTrending = ArrayList<String>()
        listTrending.add("Trending")
        listTrending.add("PercTrending1")
        listTrending.add("PercTrending2")
        listTrending.add("PercTrending3")

        val listCultural = ArrayList<String>()
        listCultural.add("Cultural")
        listCultural.add("PercCultural1")
        listCultural.add("PercCultural2")
        listCultural.add("PercCultural3")

        listOfCategories.add(listFavourites)
        listOfCategories.add(listPopular)
        listOfCategories.add(listNew)
        listOfCategories.add(listTrending)
        listOfCategories.add(listCultural)

        percursos_main_recycler.layoutManager = LinearLayoutManager(this)
        percursos_main_recycler.adapter = PercMainRecyclerAdapter(this,listOfCategories)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_profile -> {

                val intent = Intent(this, ProfileMain::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_course -> {


                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    class PercMainRecyclerAdapter(val context : Context, val listOfCategories : ArrayList<ArrayList<String>>): RecyclerView.Adapter<CustomViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

            val layoutInflater = LayoutInflater.from(parent?.context)
            val cellForRow = layoutInflater.inflate(R.layout.percursos_main_recycler_view, parent, false)
            return CustomViewHolder(cellForRow)
        }

        override fun getItemCount(): Int {

            return listOfCategories.size
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

            val getCourses = listOfCategories.get(position)

            holder.view.percursos_main_recycler_category.text = getCourses[0]
            holder.view.percursos_main_recycler_ct1_text.text = getCourses[1]
            holder.view.percursos_main_recycler_ct2_text.text = getCourses[2]
            holder.view.percursos_main_recycler_ct3_text.text = getCourses[3]

            holder.view.setOnClickListener {

                val intent = Intent(context, PercursoCategoria::class.java)
                intent.putExtra("list", getCourses)
                context.startActivity(intent)
            }
        }
    }

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){}
}
