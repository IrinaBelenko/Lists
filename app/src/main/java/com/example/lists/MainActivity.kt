package com.example.lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val api = ApiClient.client.create(ApiInterface::class.java)
        api.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.data.memes.isNotEmpty()) {
                    val items = it.data.memes
                    val myAdapter = RecyclerViewAdapter(items as MutableList<Meme>) {
                        Toast.makeText(this, "${it} clicked", Toast.LENGTH_SHORT).show()
                    }
                    recyclerView.adapter = myAdapter
                }
            }, {
                Toast.makeText(this, "Fetch error ${it.message}", Toast.LENGTH_SHORT).show()
            })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

data class MemesResponse(val data: Data)
data class Data(val memes: List<Meme>)
data class Meme(val name: String, val url: String)