package com.example.alpha10

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation .SuppressLint
import android.annotation .TargetApi
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in_sec.*

import org.json.JSONException
import org.json.JSONObject
import java.io.*

import java.net.HttpURLConnection
import java.net.URL

import javax.net.ssl.HttpsURLConnection

class SignIn : AppCompatActivity() {

    var mAuthTask: UserSigninTask? = null
    var user :JSONObject? = null
    var code : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        user = JSONObject()

        sign_in_button.setOnClickListener(View.OnClickListener {

            setContentView(R.layout.activity_sign_in_sec)
            sign_in_sec_finish.setOnClickListener {
                val int = Intent(this, LogIn::class.java)
                startActivity(int)
            }

            /*
            if (sign_in_username.text.isNotEmpty() || sign_in_password.text.isNotEmpty()) {

                user!!.accumulate("username", sign_in_username.text)
                user!!.accumulate("password", sign_in_password.text)

                setContentView(R.layout.activity_sign_in_sec)
                sign_in_sec_finish.setOnClickListener {

                    if ( sign_in_sec_email.text.isNotEmpty() || sign_in_sec_profile.text.isNotEmpty() || sign_in_sec_home_phone.text.isNotEmpty() ||
                        sign_in_sec_mobile.text.isNotEmpty() || sign_in_sec_address.text.isNotEmpty() || sign_in_sec_comp.text.isNotEmpty() ||
                        sign_in_sec_local.text.isNotEmpty() || sign_in_sec_post_code.text.isNotEmpty()) {

                        user!!.accumulate("mail", sign_in_sec_email.text)
                        user!!.accumulate("perfil", sign_in_sec_profile.text)
                        user!!.accumulate("telefone", sign_in_sec_home_phone.text)
                        user!!.accumulate("telemovel", sign_in_sec_mobile.text)
                        user!!.accumulate("morada", sign_in_sec_address.text)
                        user!!.accumulate("moradaComp", sign_in_sec_comp.text)
                        user!!.accumulate("localidade", sign_in_sec_local.text)
                        user!!.accumulate("codigoPostal", sign_in_sec_post_code.text)

                        //attemptSignin()

                    }else Toast.makeText(this, "There is something missing", Toast.LENGTH_SHORT).show()
                }
            } else Toast.makeText(this, "Please enter a valid username and/or password", Toast.LENGTH_SHORT).show()
            */
        })
    }

    /*Interacts with Internet*/
    fun attemptSignin() {

        if (mAuthTask != null) {
            return
        }
        sign_in_username.setError(null)
        sign_in_password.setError(null)

        mAuthTask = UserSigninTask()
        mAuthTask!!.execute(null)

        while(!code.equals("200")){}
        while(!mAuthTask!!.status.equals("FINISHED")){}

        val bol: Boolean = !mAuthTask!!.status.equals("FINISHED")

        if(code.equals("0")){
            Toast.makeText(this, "No code", Toast.LENGTH_SHORT).show()
        }
        else if(code.equals("404")){

            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
        }
        else if(code.equals("500")){

            Toast.makeText(this, "The server is fried", Toast.LENGTH_SHORT).show()
        }
        else if (code.equals("200")){

            Toast.makeText(this, "Successful sign in", Toast.LENGTH_SHORT).show()

            onDestroy()
            val int = Intent(this, LogIn::class.java)
            startActivity(int)
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class UserSigninTask internal constructor() :
        AsyncTask<Void, Void, String>() {

        override fun onPreExecute() {
            val connMgr = getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo

            if (networkInfo == null || !networkInfo.isConnected ||
                networkInfo.type != ConnectivityManager.TYPE_WIFI && networkInfo.type != ConnectivityManager.TYPE_MOBILE
            ) {

                cancel(true)
            }
        }

        override fun doInBackground(vararg params: Void): String? {
            try {

                return HttpRequest().doHTTP(URL("https://turisnova.appspot.com/rest/register/user"), user!!, "POST")

            } catch (e: Exception) {
                code = e.toString()
                return e.toString()
            }
        }

        override fun onPostExecute(success: String?) {

            mAuthTask = null

            if (success != null) {
                var token: JSONObject? = null
                try {

                    token = JSONObject(success)
                    Log.i("LoginActivity", token.toString())

                    val settings = getSharedPreferences("AUTHENTICATION", 0)
                    val editor = settings.edit()
                    editor.putString("tokenID", token.getString("tokenID"))

                    editor.apply()

                    code = "200"
                    finish()

                } catch (e: JSONException) {

                    Log.e("Authentication", e.toString())
                }

            } else {
                sign_in_password.setError("This password is incorrect")
                sign_in_password.requestFocus()
            }
        }

        override fun onCancelled() {

            mAuthTask = null
        }
    }
}