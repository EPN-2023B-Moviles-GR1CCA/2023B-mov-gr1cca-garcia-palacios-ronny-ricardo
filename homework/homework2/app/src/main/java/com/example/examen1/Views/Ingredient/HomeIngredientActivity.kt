package com.example.examen1.Views.Ingredient // Asegúrate de que la declaración de paquete coincida con tu configuración

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.BD
import com.example.examen1.R

class HomeIngredientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_ingredient)
        Log.i("ciclo-vida", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val listViewU = findViewById<ListView>(R.id.lv_ingredients_list)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BD.tables!!.readIngredients()
        )
        listViewU.adapter = adapter
        this.registerForContextMenu(listViewU)

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