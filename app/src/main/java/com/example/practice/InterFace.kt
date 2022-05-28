package com.example.practice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class InterFace(private val context: Context){

    fun ui(title:String,index:Int):View{
        val inflater= LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.ui,null)
        view.findViewById<TextView>(R.id.title).text=title
        view.tag=index
        val params = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100)
        view.layoutParams=params

        return view
    }
}