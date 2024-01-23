package com.example.examen1.Views.Recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R

class DeleteRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_recipe)
    }
    override fun onStart() {
        super.onStart()

        val button_recipe_delete = findViewById<android.widget.Button>(R.id.button_recipe_delete)
        val button_recipe_go_back_menu = findViewById<android.widget.Button>(R.id.button_recipe_go_back_menu)
        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_recipe_delete)

        button_recipe_go_back_menu.setOnClickListener {
            finish()
        }

        val recipes = BD.tables!!.readRecipes()
        val recipesId = recipes.map { it.id }

        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, recipesId)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button_recipe_delete.setOnClickListener {
            val id = spinner.selectedItem.toString()
            BD.tables!!.deleteRecipe(id)
            finish()
        }

    }
}