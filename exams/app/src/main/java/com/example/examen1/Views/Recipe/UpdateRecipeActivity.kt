package com.example.examen1.Views.Recipe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R
import com.google.android.material.textfield.TextInputEditText

class UpdateRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_recipe)
    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_recipe_update)
        val txt_recipe_update_name = findViewById<TextInputEditText>(R.id.txt_recipe_update_name)

        val txt_recipe_update_description = findViewById<TextInputEditText>(R.id.txt_recipe_update_description)
        val txt_recipe_update_ingredients = findViewById<TextInputEditText>(R.id.txt_recipe_update_ingredients)

        val button_recipe_update = findViewById<android.widget.Button>(R.id.button_recipe_update)
        val button_go_back_recipe_update = findViewById<android.widget.Button>(R.id.button_go_back_recipe_update)

        button_go_back_recipe_update.setOnClickListener {
            finish()
        }

        val recipes = BD.tables!!.readRecipes()
        val recipesId = recipes.map { it.id }
        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, recipesId)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        fun clearFields() {
            txt_recipe_update_name.setText("")
            txt_recipe_update_description.setText("")
            txt_recipe_update_ingredients.setText("")
        }

        spinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val recipeId = recipesId[position]
                val recipe = BD.tables!!.readRecipe(recipeId)

                if (recipe == null) return clearFields()
                txt_recipe_update_name.setText(recipe.name)
                txt_recipe_update_description.setText(recipe.description)
                txt_recipe_update_ingredients.setText(recipe.ingredients)
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                clearFields()
            }
        }

        button_recipe_update.setOnClickListener {
            val id = spinner.selectedItem.toString()
            val name = txt_recipe_update_name.text.toString()
            val description = txt_recipe_update_description.text.toString()
            val ingredients = txt_recipe_update_ingredients.text.toString()

            if (name != "" && description != "" && ingredients != "") {
                BD.tables!!.uploadRecipe(id, name, description, ingredients)
                finish()
            }
        }
    }

}