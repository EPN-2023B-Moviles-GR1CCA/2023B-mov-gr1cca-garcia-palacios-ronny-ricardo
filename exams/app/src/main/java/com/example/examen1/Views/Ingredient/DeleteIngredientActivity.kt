package com.example.examen1.Views.Ingredient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R

class DeleteIngredientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_ingredient)
    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val button_back_ingredient = findViewById<android.widget.Button>(R.id.button_back_ingredient)
        val button_ingredient_delete = findViewById<android.widget.Button>(R.id.button_ingredient_delete)
        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_ingredient_delete)

        val ingredients = BD.tables!!.readIngredients()
        val ingredientsId = ingredients.map { it.id }

        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientsId)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        button_back_ingredient.setOnClickListener {
            finish()
        }

        button_ingredient_delete.setOnClickListener {
            val id = spinner.selectedItem.toString()
            BD.tables!!.deleteIngredient(id)
            finish()
        }
    }
}