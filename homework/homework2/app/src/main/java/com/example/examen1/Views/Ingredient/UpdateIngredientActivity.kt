package com.example.examen1.Views.Ingredient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R
import com.google.android.material.textfield.TextInputEditText

class UpdateIngredientActivity :  AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_ingredient)
    }


    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val button_back_ingredient = findViewById<android.widget.Button>(R.id.button_back_ingredient)
        val button_ingredient_edit = findViewById<android.widget.Button>(R.id.button_ingredient_edit)
        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_id_ingredient)
        var txt_ingredient_update_name = findViewById<TextInputEditText>(R.id.txt_ingredient_update_name)
        var txt_ingredient_update_quantity = findViewById<TextInputEditText>(R.id.txt_ingredient_update_quantity)
        var txt_ingredient_update_unit = findViewById<TextInputEditText>(R.id.txt_ingredient_update_unit)

        button_back_ingredient.setOnClickListener {
            finish()
        }

        val ingredients = BD.tables!!.readIngredients()
        val ingredientsId = ingredients.map { it.id }
        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientsId)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        fun clearFields() {
            txt_ingredient_update_name.setText("")
            txt_ingredient_update_quantity.setText("")
            txt_ingredient_update_unit.setText("")
        }

        spinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                Log.i("ciclo-vida", "onItemSelected")
                Log.i("ciclo-vida", "position: $position")
                val ingredientId = ingredientsId[position]
                Log.i("ciclo-vida", "ingredientId: $ingredientId")
                val ingredient = BD.tables!!.readIngredient(ingredientId)
                Log.i("ciclo-vida", "ingredient: $ingredient")

                if (ingredient == null) return clearFields()
                txt_ingredient_update_name.setText(ingredient.name)
                txt_ingredient_update_quantity.setText(ingredient.quantity.toString())
                txt_ingredient_update_unit.setText(ingredient.unit)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                clearFields()
            }
        }
        button_ingredient_edit.setOnClickListener {
            val id = spinner.selectedItem.toString()
            val name = txt_ingredient_update_name.text.toString()
            val quantity = txt_ingredient_update_quantity.text.toString()
            val unit = txt_ingredient_update_unit.text.toString()
            BD.tables!!.uploadIngredient(id, name, quantity, unit)
            finish()
        }

    }
}