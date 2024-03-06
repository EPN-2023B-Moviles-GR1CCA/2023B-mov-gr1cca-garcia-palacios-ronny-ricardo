package com.example.examen1.Views.Recipe

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.BDD.RecipeManager
import com.example.examen1.R
import org.example.models.Ingredient
import org.example.models.Recipe

class HomeRecipeActivity : AppCompatActivity(){
    private lateinit var recipeManager: RecipeManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_recipe)
        Log.i("ciclo-vida", "onCreate")
        recipeManager = RecipeManager()
    }
    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val listViewU = findViewById<ListView>(R.id.lv_recipe_list_read)
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
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                recipes
            )
            listViewU.adapter = adapter
            this.registerForContextMenu(listViewU)
        }
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            BD.tables!!.readRecipes()
//        )
//        listViewU.adapter = adapter
//        this.registerForContextMenu(listViewU)

        val button_recipe_add_action = findViewById<Button>(R.id.button_recipe_add_action)
        val button_recipe_update_action = findViewById<Button>(R.id.button_recipe_update_action)
        val button_recipe_delete_action = findViewById<Button>(R.id.button_recipe_delete_action)
        val button_back_main_menu = findViewById<Button>(R.id.button_back_main_menu)

//        go back to activity main
        button_back_main_menu.setOnClickListener {
            finish()
        }
        button_recipe_add_action.setOnClickListener {
            val intent = intent
            intent.setClass(this, CreateRecipeActivity::class.java)
            startActivity(intent)
        }
        button_recipe_update_action.setOnClickListener {
            val intent = intent
            intent.setClass(this, UpdateRecipeActivity::class.java)
            startActivity(intent)
        }
        button_recipe_delete_action.setOnClickListener {
            val intent = intent
            intent.setClass(this, DeleteRecipeActivity::class.java)
            startActivity(intent)
        }

    }
}