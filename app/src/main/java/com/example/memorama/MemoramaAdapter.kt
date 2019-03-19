package com.example.memorama

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.games.R
import kotlinx.android.synthetic.main.renglon.view.*
import java.util.logging.Handler
import kotlin.random.Random

class MemoramaAdapter(val chips: ArrayList<Chip>, val textView: TextView):

    RecyclerView.Adapter<MemoramaAdapter.ChipViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChipViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.renglon, p0, false)
        return ChipViewHolder(view)
    }

    var img: ArrayList<Int> = ArrayList<Int>()
    var turno: Int = 0
    var score:ArrayList<Int> = ArrayList()
    var found: ArrayList<Int> = ArrayList()
    var id1: ImageView? = null

    init {
        img.add(R.drawable.uno)
        img.add(R.drawable.uno)
        img.add(R.drawable.dos)
        img.add(R.drawable.dos)
        img.add(R.drawable.tres)
        img.add(R.drawable.tres)
        img.add(R.drawable.cuatro)
        img.add(R.drawable.cuatro)
        img.add(R.drawable.cinco)
        img.add(R.drawable.cinco)
        img.add(R.drawable.seis)
        img.add(R.drawable.seis)
        img.add(R.drawable.siete)
        img.add(R.drawable.siete)
        img.add(R.drawable.rocket)
        img.add(R.drawable.rocket)
        score.add(0)
        score.add(0)
        shuffle()
    }

    override fun getItemCount(): Int {
        return  chips.size
    }

    override fun onBindViewHolder(p0: ChipViewHolder, p1: Int) {
        //p0.imageView.setTag(1, "_1")
        println(p1)
        p0.imageView.setId(p1)
        p0.imageView.setImageResource(chips[p1].idImage)

    }

    fun shuffle(){
        for (i in 0..15)
            for(j in 0..(img.size - 2)){
                val s = Random.nextInt(0,img.size - 1)
                var aux = img[s]
                img.removeAt(s)
                img.add(aux)
            }
    }

    fun play(id: ImageView){
        if(id1 == null) {
            id1 = id
            found.add(id.getId())
        }else {
            if(img[id1!!.getId()] == img[id.getId()]){
                var color = Color.RED
                if(turno == 0) color = Color.BLUE
                id1!!.setBackgroundColor(color)
                id.setBackgroundColor(color)
                score.set(turno, score[turno] + 1)
                found.add(id.getId())
                id1 = null
                textView.setText("score: ${score[0]} player 1, ${score[1]} player 2")
                if((score[0]+score[1])==8){
                    if(score[0]>score[1])
                    {
                        textView.setText("PLAYER ONE WINS!")
                        score[0] = 0
                        score[1] = 1
                        //shuffle()
                    }
                    else if(score[0]==score[1])
                    {
                        textView.setText("TIE!!!")
                        score[0] = 0
                        score[1] = 1
                        //shuffle()
                    }
                    else
                    {
                        textView.setText("PLAYER TWO WINS")
                        score[0] = 0
                        score[1] = 1
                        //shuffle()
                    }
                }

            }else{
                android.os.Handler().postDelayed(Runnable {
                    id1!!.setImageResource(R.mipmap.ic_launcher)
                    id.setImageResource(R.mipmap.ic_launcher)
                    found.remove(id1!!.getId())
                    id1 = null
                    turno = (turno + 1) % 2
                },500)

            }

        }
    }

    inner class ChipViewHolder(item : View) : RecyclerView.ViewHolder(item){
        val imageView = item.findViewById<ImageView>(R.id.chip)
        init {
            item.setOnClickListener {
                if(!found.contains(imageView.getId()))
                imageView.setImageResource(img[imageView.getId()])
                println("${imageView.getId()}")
                play(imageView)
            }
        }
    }

}
