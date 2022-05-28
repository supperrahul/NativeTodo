package com.example.practice

import android.app.Dialog
import android.app.KeyguardManager
import android.content.DialogInterface
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.view.inputmethod.InputMethodManager

import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.practice.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class SecondActivity:AppCompatActivity() {
    lateinit  var binding:ActivityMainBinding
    val items:MutableList<String> = mutableListOf()
    lateinit var linear:LinearLayout
    val interFace = InterFace(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_second)
        title="Native Todo"
        linear=findViewById(R.id.linear)
        loadUI()
        window.navigationBarColor=getColor(R.color.nav_color)
        window.statusBarColor=getColor(R.color.black)
    }

    private fun loadUI() {
        linear.removeAllViews()
        var index= 0
        items.forEach {
            val view = interFace.ui(it,index)
            linear.addView(view)
            var i  = view.tag as Int
           val btn =  view.findViewById<Button>(R.id.delete)
            btn.setOnClickListener{
                deleteUI(i)
            }
            index++
        }
    }

    private fun deleteUI(index: Int) {
        Toast.makeText(applicationContext, "item no ${index+1} deleted", Toast.LENGTH_SHORT).show()
        items.removeAt(index)
        loadUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_file,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add->{
                   showdialog()
                }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun showdialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val et = dialog.findViewById<TextInputEditText>(R.id.value)
        dialog.findViewById<Button>(R.id.addbtn).setOnClickListener {
            if (et.text.toString()!="") {
                        items.add(et.text.toString())
                     Toast.makeText(applicationContext, "added "+et.text, Toast.LENGTH_SHORT).show()
                        loadUI()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, "Enter task and try again", Toast.LENGTH_SHORT).show()
                    }
        }

        dialog.findViewById<Button>(R.id.cancenbtn).setOnClickListener {
            dialog.dismiss()
        }

            Handler(Looper.myLooper()!!).postDelayed({
                                    et.requestFocus()
                                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                                    imm.showSoftInput(et,InputMethodManager.SHOW_IMPLICIT)
                    },500)

        dialog.show()
    }
}