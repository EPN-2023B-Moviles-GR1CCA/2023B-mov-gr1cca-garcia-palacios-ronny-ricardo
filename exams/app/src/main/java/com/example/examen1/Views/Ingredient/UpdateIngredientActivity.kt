package com.example.examen1.Views.Ingredient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examen1.BDD.IngredientManager
import com.example.examen1.R
import com.google.android.material.textfield.TextInputEditText
import org.example.models.Ingredient

class UpdateIngredientActivity :  AppCompatActivity(){
    private lateinit var ingredientManager: IngredientManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_ingredient)
        ingredientManager = IngredientManager()
    }


    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        val button_back_ingredient = findViewById<android.widget.Button>(R.id.button_back_ingredient)
        val button_ingredient_edit = findViewById<android.widget.Button>(R.id.button_ingredient_edit)
        val spinner = findViewById<android.widget.Spinner>(R.id.spinner_id_ingredient)
        var txt_ingredient_update_name = findViewById<TextInputEditText>(R.id.txt_ingredient_update_name)
        var txt_ingredient_update_quantity = findViewById<TextInputEditText>(R.id.txt_ingredient_update_quantity)
        var txt_ingredient_update_unit = findViewById<TextInputEditText>(R.id.txt_ingredient_update_unit)

        button_back_ingredient.setOnClickListener {
            finish()
        }
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
            val ingredientsId = ingredients.map { it.id }
            val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientsId)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            fun clearFields() {
                txt_ingredient_update_name.setText("")
                txt_ingredient_update_quantity.setText("")
                txt_ingredient_update_unit.setText("")
            }

            spinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                    val ingredientId = ingredientsId[position]
                    ingredientManager.readIngredient(ingredientId).addOnSuccessListener { result ->
                        val ingredient = Ingredient(
                            result.id,
                            result.data!!["name"] as String,
                            (result.data!!["quantity"] as Long).toInt(),
                            result.data!!["unit"] as String
                        )
                        txt_ingredient_update_name.setText(ingredient.name)
                        txt_ingredient_update_quantity.setText(ingredient.quantity.toString())
                        txt_ingredient_update_unit.setText(ingredient.unit)
                    }
//                    val ingredient = ingredientManager.readIngredient(ingredientId)
//                    if (ingredient == null) return clearFields()
//                    txt_ingredient_update_name.setText(ingredient.name)
//                    txt_ingredient_update_quantity.setText(ingredient.quantity.toString())
//                    txt_ingredient_update_unit.setText(ingredient.unit)
                }

                override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                    clearFields()
                }
            }
            button_ingredient_edit.setOnClickListener {
                val id = spinner.selectedItem.toString()
                val name = txt_ingredient_update_name.text.toString()
                val quantity = txt_ingredient_update_quantity.text.toString()
                val unit = txt_ingredient_update_unit.text.toString()
                ingredientManager.uploadIngredient(id, name, quantity.toLong(), unit)
//                BD.tables!!.uploadIngredient(id, name, quantity, unit)
                finish()
            }
        }

//        val ingredients = BD.tables!!.readIngredients()
//        val ingredientsId = ingredients.map { it.id }
//        val adapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, ingredientsId)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter

//        fun clearFields() {
//            txt_ingredient_update_name.setText("")
//            txt_ingredient_update_quantity.setText("")
//            txt_ingredient_update_unit.setText("")
//        }
//
//        spinner.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
//                Log.i("ciclo-vida", "onItemSelected")
//                Log.i("ciclo-vida", "position: $position")
//                val ingredientId = ingredientsId[position]
//                Log.i("ciclo-vida", "ingredientId: $ingredientId")
//                val ingredient = BD.tables!!.readIngredient(ingredientId)
//                Log.i("ciclo-vida", "ingredient: $ingredient")
//
//                if (ingredient == null) return clearFields()
//                txt_ingredient_update_name.setText(ingredient.name)
//                txt_ingredient_update_quantity.setText(ingredient.quantity.toString())
//                txt_ingredient_update_unit.setText(ingredient.unit)
//            }
//
//            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
//                clearFields()
//            }
//        }
//        button_ingredient_edit.setOnClickListener {
//            val id = spinner.selectedItem.toString()
//            val name = txt_ingredient_update_name.text.toString()
//            val quantity = txt_ingredient_update_quantity.text.toString()
//            val unit = txt_ingredient_update_unit.text.toString()
//            BD.tables!!.uploadIngredient(id, name, quantity, unit)
//            finish()
//        }

    }
}