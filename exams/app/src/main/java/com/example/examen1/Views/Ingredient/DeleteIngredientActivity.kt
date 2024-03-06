package com.example.examen1.Views.Ingredient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.BDD.IngredientManager
import com.example.examen1.R
import org.example.models.Ingredient

class DeleteIngredientActivity : AppCompatActivity() {
    private lateinit var ingredientManager: IngredientManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_ingredient)
        ingredientManager = IngredientManager()
    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val button_back_ingredient = findViewById<android.widget.Button>(R.id.button_back_ingredient)
        val button_ingredient_delete = findViewById<android.widget.Button>(R.id.button_ingredient_delete)
        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_ingredient_delete)

        ingredientManager.readIngredients().addOnSuccessListener { result ->
            val ingredients = arrayListOf<Ingredient>()
            for (document in result) {
                Log.i("docIngredient", "document.data: ${document.data}")
                val ingredient = Ingredient(
                    document.id,
                    document.data["name"] as String,
                    (document.data["quantity"] as Long).toInt(),
                    document.data["unit"] as String
                )
                ingredients.add(ingredient)
            }
            val ingredientsId = ingredients.map { it.id }

            val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientsId)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter


            button_back_ingredient.setOnClickListener {
                finish()
            }

            button_ingredient_delete.setOnClickListener {
                val id = spinner.selectedItem.toString()
//                BD.tables!!.deleteIngredient(id)
                ingredientManager.deleteIngredient(id)
                finish()
            }
        }

//        val ingredients = BD.tables!!.readIngredients()
//        val ingredientsId = ingredients.map { it.id }
//
//        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientsId)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
//
//
//        button_back_ingredient.setOnClickListener {
//            finish()
//        }
//
//        button_ingredient_delete.setOnClickListener {
//            val id = spinner.selectedItem.toString()
//            BD.tables!!.deleteIngredient(id)
//            finish()
//        }
    }
}