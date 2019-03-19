package com.example.memorama

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import com.example.games.R
import kotlinx.android.synthetic.main.activity_memorama2.*

class MemoramaActivity : AppCompatActivity() {

    var turn: Int = 0
    var firstCard: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memorama2)
        val rv = recyclerView1
        rv.setHasFixedSize(true)
        var grid = GridLayoutManager(this, 4)
        rv.layoutManager = grid

        val chips = ArrayList<Chip>()
        for( i  in 0..15)
            chips.add(Chip(R.mipmap.ic_launcher, i))
        var poo = findViewById<TextView>(R.id.textView)
        var adapter = MemoramaAdapter(chips, poo)
        rv.adapter = adapter
        //iv.setImageResource(R.drawable.space)
    }

    companion object {
        fun a() : Int{ return 0}
    }
    fun newGame(){
        val ticIntent = Intent(this, MemoramaActivity::class.java).apply {
            putExtra("name", "Memorama")
        }
        startActivity(ticIntent)
    }


    /*companion object {
        fun caller(id: Int): super.compareCard(id)
    }*/

    /*fun compareCard(id: Int){


    }*/

}
