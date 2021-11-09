package com.example.connectapi


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.ListView

import connectAPI.Model.Todo
import connectAPI.Model.TodoArrayAdapter
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mylist)
        getTodoList()
    }
    fun getTodoList() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/todos")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val todos = JSONArray(response.body!!.string())
                    var aList = arrayListOf<Todo>()
                    for (i in 0 until todos.length()) {
                        var dataInner: JSONObject = todos.getJSONObject(i)
                        Log.d(">>>>>>",dataInner.getString("id"))
                        aList.add(
                            Todo(
                                dataInner.getString("userId"),
                                dataInner.getString("id"),
                                dataInner.getString("title"),
                                dataInner.getString("completed"),
                            ))
                    }
                    runOnUiThread {
                        val adapter = TodoArrayAdapter(this@MainActivity, aList)
                        var myListView = findViewById<ListView>(R.id.mylist)
                        myListView.adapter = adapter

                        myListView.setOnItemClickListener { _, _, position, _ ->
                            var intent: Intent = Intent(this@MainActivity, Detail::class.java)

                            // Pass the values to next activity (StationActivity)
                            intent!!.putExtra("id",aList[position].id)
                            intent!!.putExtra("title",aList[position].title)

                            startActivity(intent)
                        }
                    }
                }
            }
        })
    }
}

