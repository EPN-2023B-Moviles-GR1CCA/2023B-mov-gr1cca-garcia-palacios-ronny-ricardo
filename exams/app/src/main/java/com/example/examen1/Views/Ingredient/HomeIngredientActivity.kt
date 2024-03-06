package com.example.examen1.Views.Ingredient // Asegúrate de que la declaración de paquete coincida con tu configuración

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.BDD.IngredientManager
import com.example.examen1.R
import org.example.models.Ingredient

class HomeIngredientActivity : AppCompatActivity() {
    private lateinit var ingredientManager: IngredientManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_ingredient)
        Log.i("ciclo-vida", "onCreate")
        ingredientManager = IngredientManager()
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val listViewU = findViewById<ListView>(R.id.lv_ingredients_list)
        ingredientManager.readIngredients().addOnSuccessListener { result ->
            val ingredients = arrayListOf<Ingredient>()

//            val ingredients = result.toObjects(Ingredient::class.java)
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
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                ingredients
            )
            listViewU.adapter = adapter
            this.registerForContextMenu(listViewU)
        }
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            ingredientManager.readIngredients()
//        )
//        listViewU.adapter = adapter
//        this.registerForContextMenu(listViewU)
        val button_ingredient_menu_add = findViewById<Button>(R.id.button_ingredient_menu_add)
        val button_ingredient_menu_edit = findViewById<Button>(R.id.button_ingredient_menu_edit)
        val button_ingredient_menu_delete = findViewById<Button>(R.id.button_ingredient_menu_delete)
        val button_menu_back = findViewById<Button>(R.id.button_menu_back)

//        go back to activity main
        button_menu_back.setOnClickListener {
            finish()
        }
        button_ingredient_menu_add.setOnClickListener {
            val intent = intent
            intent.setClass(this, CreateIngredientActivity::class.java)
            startActivity(intent)
        }
        button_ingredient_menu_delete.setOnClickListener {
            val intent = intent
            intent.setClass(this, DeleteIngredientActivity::class.java)
            startActivity(intent)
        }
        button_ingredient_menu_edit.setOnClickListener {
            val intent = intent
            intent.setClass(this, UpdateIngredientActivity::class.java)
            startActivity(intent)
        }

    }
}
