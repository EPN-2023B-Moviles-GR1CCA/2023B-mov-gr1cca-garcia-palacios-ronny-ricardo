package com.example.examen1.Views.Ingredient

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R
import com.google.android.material.textfield.TextInputEditText

class CreateIngredientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ingredient)
    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val button_menu_back = findViewById<Button>(R.id.button_back_ingredient)
        button_menu_back.setOnClickListener {
            finish()
        }
        var txt_ingredient_add_name = findViewById<TextInputEditText>(R.id.txt_ingredient_add_name)
        var txt_ingredient_add_quantity = findViewById<TextInputEditText>(R.id.txt_ingredient_add_quantity)
        var text_ingredient_add_unit = findViewById<TextInputEditText>(R.id.text_ingredient_add_unit)
        var btn_ingredient_add = findViewById<Button>(R.id.btn_ingredient_add)

        btn_ingredient_add.setOnClickListener {
            var name = txt_ingredient_add_name.text.toString()
            var quantity = txt_ingredient_add_quantity.text.toString()
            var unit = text_ingredient_add_unit.text.toString()

            if (name != "" && quantity != "" && unit != "") {
                val uuid = java.util.UUID.randomUUID().toString()
                BD.tables!!.createIngredient(uuid,name, quantity, unit)
                finish()
            }
        }
    }

}