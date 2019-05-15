package com.example.alpha10

import android.app.Activity
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.util.Log
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HttpRequest (){

    @Throws(IOException::class)
    fun doHTTP(url: URL, data: JSONObject, method: String): String? {

        var stream: InputStream? = null
        var out: OutputStream? = null
        var connection: HttpURLConnection? = null
        var result: String? = null

        try {

            connection = url.openConnection() as HttpURLConnection
            connection.readTimeout = 10000
            connection.connectTimeout = 10000
            connection.requestMethod = method
            connection.doInput = true
            connection.setChunkedStreamingMode(0)
            connection.setRequestProperty("Accept", "application/json")
            connection.setRequestProperty("Content-type", "application/json")

            out = BufferedOutputStream(connection.outputStream)
            out.write(data.toString().toByteArray())
            out.flush()
            val responseCode = connection.responseCode
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw IOException(responseCode.toString())
            }
            stream = connection.inputStream
            if (stream != null) {

                result = readStream(stream, 1024)
            }
        } finally {

            out?.close()
            stream?.close()
            connection?.disconnect()
        }
        return result
    }

    @Throws(IOException::class)
    fun readStream(stream: InputStream, length: Int): String {

        val res: String
        val b: ByteArray? = null

        stream.read(b!!, 0, length)

        res = b.toString()

        return res
    }

    fun ConvertStreamToString(inputStream: InputStream): String {

        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var AllString: String = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    AllString += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: java.lang.Exception) {
        }

        return AllString
    }
}
