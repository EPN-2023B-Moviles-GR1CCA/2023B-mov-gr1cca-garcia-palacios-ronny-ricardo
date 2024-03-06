package com.example.examen1.Views.Recipe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.RecipeManager
import com.example.examen1.R
import org.example.models.Recipe

class DeleteRecipeActivity : AppCompatActivity() {
    private lateinit var recipeManager: RecipeManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_recipe)
        recipeManager = RecipeManager()
    }
    override fun onStart() {
        super.onStart()

        val button_recipe_delete = findViewById<android.widget.Button>(R.id.button_recipe_delete)
        val button_recipe_go_back_menu = findViewById<android.widget.Button>(R.id.button_recipe_go_back_menu)
        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_recipe_delete)

        button_recipe_go_back_menu.setOnClickListener {
            finish()
        }
        recipeManager.readRecipes().addOnSuccessListener { result ->
            val recipes = arrayListOf<Recipe>()
            for (document in result) {
                Log.i("docRecipe", "document.data: ${document.data}")
                val recipe = Recipe(
                    document.id,
                    document.data["name"] as String,
                    document.data["description"] as String,
                    document.data["ingredients"] as String
                )
                recipes.add(recipe)
            }
            val recipesId = recipes.map { it.id }

            val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, recipesId)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            button_recipe_delete.setOnClickListener {
                val id = spinner.selectedItem.toString()
                recipeManager.deleteRecipe(id)
                finish()
            }
        }
    }
}