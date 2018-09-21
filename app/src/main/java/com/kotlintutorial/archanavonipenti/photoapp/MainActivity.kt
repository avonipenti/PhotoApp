package com.kotlintutorial.archanavonipenti.photoapp

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.kotlintutorial.archanavonipenti.photoapp.api.PhotoRetriever
import com.kotlintutorial.archanavonipenti.photoapp.models.Photo
import com.kotlintutorial.archanavonipenti.photoapp.models.PhotoList

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.CacheResponse

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var photos : List<Photo>? = null
    var mainAdapter : MainAdapter? = null
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        var retriever = PhotoRetriever()

        val callback = object : Callback<PhotoList> {
            override fun onResponse(call: Call<PhotoList>?, response:
            Response<PhotoList>?) {
                response?.isSuccessful.let {
                    this@MainActivity.photos = response?.body()?.hits
                    mainAdapter = MainAdapter(this@MainActivity.photos!!,
                            this@MainActivity)
                    recyclerView.adapter = mainAdapter
                }
            }

            override  fun onFailure(call: Call<PhotoList>?, t:Throwable?) {
                Log.e("MainActivity", "Problems calling API", t)
            }
        }

        retriever.getPhotos(callback)
    }

    override fun onClick(view: View?) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
