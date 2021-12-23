package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

   private var listview : ListView?=null
   private var editTextFilter:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        connectView()
        prepareListView()
    }
    private fun connectView(){

        listview = findViewById(R.id.list)
        editTextFilter = findViewById(R.id.etFilter)
    }
    private fun prepareListView(){

        /*
        1) this (context)
        2) layout (to display data)
        3) Data source (Array)
         */
        val array:ArrayList<String> = arrayListOf()
        array.add("Orange")
        array.add("Apple")
        array.add("Cherry")
        array.add("Banana")
        array.add("Cucumber")

    val arrayAdapter:ArrayAdapter<String> = ArrayAdapter(this
        ,R.layout.support_simple_spinner_dropdown_item,array)

        // connect list view with data
        listview?.adapter = arrayAdapter

        listview?.setOnItemClickListener { parent, view, position, id ->

            Log.i("Item removed","this item is removed ${array.get(position)}")
            array.removeAt(position)

            //we call this function to update list view
            arrayAdapter.notifyDataSetChanged()
        }
        // filter search for item with 2 code only
        editTextFilter?.addTextChangedListener {
            arrayAdapter.filter.filter(it)
        }

    }
}
