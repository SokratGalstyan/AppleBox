package com.example.applebox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sumbit = findViewById<Button>(R.id.sumbit)
        val init = findViewById<EditText>(R.id.init_quantity)
        val max = findViewById<EditText>(R.id.max_quantity)


        sumbit.setOnClickListener {
            try {
                val i = init.text.toString().toInt()
                val m = max.text.toString().toInt()

                if (m < 0 || i < 0){
                    Toast.makeText(this, "Quantities cann\'t be negative", Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtra("init", i)
                    intent.putExtra("max", m)
                    startActivity(intent)
                }


            }
            catch (nfe: NumberFormatException){
                Toast.makeText(this, "Please Enter Valid Integer Number", Toast.LENGTH_SHORT).show()
            }
        }

    }
}