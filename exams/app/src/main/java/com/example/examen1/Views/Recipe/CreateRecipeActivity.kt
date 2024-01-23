package com.example.examen1.Views.Recipe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R
import com.google.android.material.textfield.TextInputEditText

class CreateRecipeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val button_menu_back = findViewById<Button>(R.id.button_go_back_menu_recipe)
        button_menu_back.setOnClickListener {
            finish()
        }
        val text_recipe_add_name = findViewById<TextInputEditText>(R.id.text_recipe_add_name)
        val text_recipe_add_description = findViewById<TextInputEditText>(R.id.text_recipe_add_description)
        val text_recipe_add_ingredients = findViewById<TextInputEditText>(R.id.text_recipe_add_ingredients)
        val button_add_recipe_save = findViewById<Button>(R.id.button_add_recipe_save)

        button_add_recipe_save.setOnClickListener {
            val name = text_recipe_add_name.text.toString()
            val description = text_recipe_add_description.text.toString()
            val ingredients = text_recipe_add_ingredients.text.toString()

            if (name != "" && description != "" && ingredients != "") {
                val uuid = java.util.UUID.randomUUID().toString()
                BD.tables!!.createRecipe(uuid, name, description, ingredients)
                finish()
            }
        }
    }
}