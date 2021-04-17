package com.example.applebox

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() {
    private lateinit var quantityText: TextView
    private lateinit var resetButton: Button
    private lateinit var context: Context
    private var init = 0
    private var max = 0
    private var quantity = 0
    private var resetVisible = false
    get() = quantity==0 || quantity==max
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init = this.intent.getIntExtra("init", 0)
        max = this.intent.getIntExtra("max",0)
        quantity = init
        context = this
        resetButton = findViewById(R.id.reset)
        quantityText = findViewById(R.id.quantity)

        resetButton.isVisible = resetVisible
        quantityText.text = quantity.toString()

        resetButton.setOnClickListener {
            quantity = init
            quantityText.text = quantity.toString()
            resetButton.isVisible = resetVisible
        }
    }

    fun changeQuantity(view: View){
        var msg = ""
        GlobalScope.launch(Dispatchers.IO){
            when (view) {
                findViewById<Button>(R.id.take) -> {
                    if (quantity > 0) {
                        quantity--
                        msg = "An aplle taked from box"
                    } else {
                        msg = "Box is empty"
                    }
                }
                findViewById<Button>(R.id.put) -> {
                        if (quantity < max) {
                            quantity++
                            msg = "An apple putted to box"
                        } else {
                            msg = "Box is full"
                        }
                }
            }

            withContext(Dispatchers.Main){
                Toast.makeText(context, msg,Toast.LENGTH_SHORT).show()
                quantityText.text = quantity.toString()
                resetButton.isVisible = resetVisible
            }
        }


    }
}