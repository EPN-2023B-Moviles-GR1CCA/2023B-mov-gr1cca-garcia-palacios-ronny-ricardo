package org.example.reprositories

import com.google.gson.Gson
import org.example.models.Ingredient
import java.io.File
import java.io.IOException

object RIngredient {
    private val gson = Gson()
    private const val FILEPATH = "src/main/resources/ingredients.json"
    private val file = File(FILEPATH).absolutePath
    private val ingredients: MutableList<Ingredient> = loadRecipes()

    private fun loadRecipes(): MutableList<Ingredient> {
        return try {
            val content = File(file).readText()
            gson.fromJson(content, Array<Ingredient>::class.java).toMutableList()
        } catch (e: IOException) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    fun getIngredients(): List<Ingredient> {
        return ingredients.toList()
    }

    fun getIngredient(id: String): Ingredient? {
        return ingredients.find { it.id == id }
    }

    fun createIngredient(ingredient: Ingredient): Boolean {
        ingredients.add(ingredient)
        return save()

    }

    fun updateIngredient(ingredient: Ingredient): Boolean {
        val index = ingredients.indexOfFirst { it.id == ingredient.id }
        if (index < 0) return false
        ingredients[index] = ingredient
        return save()
    }

    fun deleteIngredient(id: String): Boolean {
        val index = ingredients.indexOfFirst { it.id == id }
        if (index < 0) return false
        ingredients.removeAt(index)
        return save()
    }

    private fun save(): Boolean {
        return try {
            val content = gson.toJson(ingredients)
            File(file).writeText(content)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

}