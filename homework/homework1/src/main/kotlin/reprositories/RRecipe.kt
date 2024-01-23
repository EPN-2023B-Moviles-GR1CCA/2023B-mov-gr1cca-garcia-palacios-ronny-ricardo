package org.example.reprositories

import com.google.gson.Gson
import org.example.models.Recipe
import java.io.File
import java.io.IOException

object RRecipe {
    private val gson = Gson()
    private const val FILEPATH = "src/main/resources/recipes.json"
    private val file = File(FILEPATH).absolutePath
    private val recipes: MutableList<Recipe> = loadRecipes()

    private fun loadRecipes(): MutableList<Recipe> {
        return try {
            val content = File(file).readText()
            gson.fromJson(content, Array<Recipe>::class.java).toMutableList()
        } catch (e: IOException) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    fun getRecipes(): List<Recipe> {
        return recipes.toList()
    }

    fun getRecipe(id: String): Recipe? {
        return recipes.find { it.id == id }
    }

    fun createRecipe(recipe: Recipe): Boolean {
        recipes.add(recipe)
        return save()
    }

    fun updateRecipe(recipe: Recipe): Boolean {
        val index = recipes.indexOfFirst { it.id == recipe.id }
        if (index < 0) return false
        recipes[index] = recipe
        return save()
    }

    fun deleteRecipe(id: String): Boolean {
        val index = recipes.indexOfFirst { it.id == id }
        if (index < 0) return false
        recipes.removeAt(index)
        return save()
    }

    private fun save(): Boolean {
        return try {
            val content = gson.toJson(recipes)
            File(file).writeText(content)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
}