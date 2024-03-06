package com.example.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen1.BDD.BD
import com.example.examen1.BDD.ConfigSQLite
import com.example.examen1.Views.Ingredient.HomeIngredientActivity
import com.example.examen1.Views.Recipe.HomeRecipeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BD.tables = ConfigSQLite(this)
        BD.tables!!.deleteAll()
        BD.tables!!.createInitIngredients()
        BD.tables!!.createInitRecipes()
        val btnStartIngredient = findViewById<Button>(R.id.button_start_ingredient)
        val btnStartRecipe = findViewById<Button>(R.id.button_start_recipe)
        btnStartIngredient.setOnClickListener {
            val intent = Intent(this, HomeIngredientActivity::class.java)
            startActivity(intent)
        }
        btnStartRecipe.setOnClickListener {
            val intent = Intent(this, HomeRecipeActivity::class.java)
            startActivity(intent)
        }
    }
}