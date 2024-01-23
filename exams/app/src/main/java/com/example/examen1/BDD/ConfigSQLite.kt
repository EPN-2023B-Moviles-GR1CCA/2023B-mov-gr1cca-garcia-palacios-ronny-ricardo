package com.example.examen1.BDD

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import org.example.models.Ingredient
import org.example.models.Recipe

class ConfigSQLite(
    context: Context?,
) : SQLiteOpenHelper(context, "mobiles", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreateTables: ArrayList<String> = arrayListOf(
            """
                CREATE TABLE INGREDIENT(
                id VARCHAR(50) PRIMARY KEY,
                name VARCHAR(50),
                quantity VARCHAR(50),
                unit VARCHAR(50)
                );
              """, """
                CREATE TABLE RECIPE(
                id VARCHAR(50) PRIMARY KEY,
                name VARCHAR(50),
                description VARCHAR(100),
                ingredients TEXT
                );
             """
        )
        for (i in sqlCreateTables) {
            db!!.execSQL(i)
        }
        Log.i("creart", "TABLES")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun createIngredient(id: String, name: String, quantity: String, unit: String): Boolean {
        val cursor = writableDatabase
        val ingredient = ContentValues()
        ingredient.put("id", id)
        ingredient.put("name", name)
        ingredient.put("quantity", quantity)
        ingredient.put("unit", unit)
        val result = cursor
            .insert(
                "INGREDIENT",
                null,
                ingredient
            )
        cursor.close()
        return result.toInt() != -1
    }

    fun readIngredients(): ArrayList<Ingredient> {
        val cursor = readableDatabase
        val ingredients = arrayListOf<Ingredient>()
        val query = cursor.rawQuery("SELECT * FROM INGREDIENT", null)
        while (query.moveToNext()) {
            val ingredient = Ingredient(
                query.getString(0),
                query.getString(1),
                query.getInt(2),
                query.getString(3)
            )
            ingredients.add(ingredient)
        }
        query.close()
        cursor.close()
        return ingredients
    }
    fun readIngredient(id: String): Ingredient? {
        val cursor = readableDatabase
        val query = cursor.rawQuery("SELECT * FROM INGREDIENT WHERE id = ?", arrayOf(id))
        if (query.moveToFirst()) {
            val ingredient = Ingredient(
                query.getString(0),
                query.getString(1),
                query.getInt(2),
                query.getString(3)
            )
            query.close()
            cursor.close()
            return ingredient
        }
        query.close()
        cursor.close()
        return null
    }

    fun uploadIngredient(id: String, name: String, quantity: String, unit: String): Boolean {
        val cursor = writableDatabase
        val ingredient = ContentValues()
        ingredient.put("id", id)
        ingredient.put("name", name)
        ingredient.put("quantity", quantity)
        ingredient.put("unit", unit)
        val result = cursor
            .update(
                "INGREDIENT",
                ingredient,
                "id = ?",
                arrayOf(id)
            )
        cursor.close()
        return result != -1
    }

    fun deleteIngredient(id: String): Boolean {
        val cursor = writableDatabase
        val result = cursor
            .delete(
                "INGREDIENT",
                "id = ?",
                arrayOf(id)
            )
        cursor.close()
        return result != -1
    }

    fun createRecipe(id: String, name: String, description: String, ingredients: String): Boolean {
        val cursor = writableDatabase
        val recipe = ContentValues()
        recipe.put("id", id)
        recipe.put("name", name)
        recipe.put("description", description)
        recipe.put("ingredients", ingredients)
        val result = cursor
            .insert(
                "RECIPE",
                null,
                recipe
            )
        cursor.close()
        return result.toInt() != -1
    }

    fun readRecipes(): ArrayList<Recipe> {
        val cursor = readableDatabase
        val recipes = arrayListOf<Recipe>()
        val query = cursor.rawQuery("SELECT * FROM RECIPE", null)
        while (query.moveToNext()) {
            val recipe = Recipe(
                query.getString(0),
                query.getString(1),
                query.getString(2),
                query.getString(3)
            )
            recipes.add(recipe)
        }
        query.close()
        cursor.close()
        return recipes
    }
    fun readRecipe(id: String): Recipe? {
        val cursor = readableDatabase
        val query = cursor.rawQuery("SELECT * FROM RECIPE WHERE id = ?", arrayOf(id))
        var recipe: Recipe? = null
        if (query.moveToFirst()) {
            recipe = Recipe(
                query.getString(0),
                query.getString(1),
                query.getString(2),
                query.getString(3)
            )
        }
        query.close()
        cursor.close()
        return recipe
    }

    fun uploadRecipe(id: String, name: String, description: String, ingredients: String): Boolean {
        val cursor = writableDatabase
        val recipe = ContentValues()
        recipe.put("id", id)
        recipe.put("name", name)
        recipe.put("description", description)
        recipe.put("ingredients", ingredients)
        val result = cursor
            .update(
                "RECIPE",
                recipe,
                "id = ?",
                arrayOf(id)
            )
        cursor.close()
        return result != -1
    }

    fun deleteRecipe(id: String): Boolean {
        val cursor = writableDatabase
        val result = cursor
            .delete(
                "RECIPE",
                "id = ?",
                arrayOf(id)
            )
        cursor.close()
        return result != -1
    }

    fun createInitIngredients() {
        createIngredient(java.util.UUID.randomUUID().toString(), "Leche", "1", "Litro")
        createIngredient(java.util.UUID.randomUUID().toString(), "Huevos", "6", "Unidades")
        createIngredient(java.util.UUID.randomUUID().toString(), "Harina", "1", "Kilogramo")
        createIngredient(java.util.UUID.randomUUID().toString(), "Azucar", "1", "Kilogramo")
        createIngredient(java.util.UUID.randomUUID().toString(), "Mantequilla", "1", "Kilogramo")
    }

    fun createInitRecipes() {
        createRecipe(java.util.UUID.randomUUID().toString(), "Pastel", "Torta de chocolate con leche", "1,2,3,4,5")
        createRecipe(java.util.UUID.randomUUID().toString(), "Galletas", "Galletas de mantequilla", "3,4,5")
        createRecipe(java.util.UUID.randomUUID().toString(), "Pan", "Pan de harina", "3,5")
    }
    fun deleteAll(){
        val cursor = writableDatabase
        cursor.delete("INGREDIENT", null, null)
        cursor.delete("RECIPE", null, null)
        cursor.close()
    }

}