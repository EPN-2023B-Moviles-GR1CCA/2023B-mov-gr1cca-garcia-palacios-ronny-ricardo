package com.example.examen1.Views.Ingredient

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.IngredientManager
import com.example.examen1.R
import com.google.android.material.textfield.TextInputEditText
import org.example.models.Ingredient

class CreateIngredientActivity : AppCompatActivity() {

    private lateinit var ingredientManager: IngredientManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ingredient)

        ingredientManager = IngredientManager()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val buttonMenuBack = findViewById<Button>(R.id.button_back_ingredient)
        buttonMenuBack.setOnClickListener {
            finish()
        }

        val txtIngredientAddName = findViewById<TextInputEditText>(R.id.txt_ingredient_add_name)
        val txtIngredientAddQuantity = findViewById<TextInputEditText>(R.id.txt_ingredient_add_quantity)
        val textIngredientAddUnit = findViewById<TextInputEditText>(R.id.text_ingredient_add_unit)
        val btnIngredientAdd = findViewById<Button>(R.id.btn_ingredient_add)

        btnIngredientAdd.setOnClickListener {
            val name = txtIngredientAddName.text.toString()
            val quantity = txtIngredientAddQuantity.text.toString()
            val unit = textIngredientAddUnit.text.toString()

            if (name.isNotEmpty() && quantity.isNotEmpty() && unit.isNotEmpty()) {
                val uuid = java.util.UUID.randomUUID().toString()
                ingredientManager.createIngredient(uuid, name, quantity.toLong(), unit)
                finish()
            }
        }
    }
}
