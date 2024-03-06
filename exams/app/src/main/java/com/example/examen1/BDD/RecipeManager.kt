package com.example.examen1.BDD

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class RecipeManager {
    private val db = FirebaseFirestore.getInstance()
    private val recipesCollection = db.collection("recipes")
    fun createRecipe(id: String, name: String, description: String, ingredients: String): Boolean {
        val recipe = hashMapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "ingredients" to ingredients
        )
        recipesCollection.document(id).set(recipe)
        return true
    }

    fun readRecipes(): Task<QuerySnapshot> {
        return recipesCollection.get()
    }

    fun readRecipe(id: String): Task<DocumentSnapshot> {
        return recipesCollection.document(id).get()
    }

    fun uploadRecipe(id: String, name: String, description: String, ingredients: String): Boolean {
        val recipe = hashMapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "ingredients" to ingredients
        )
        recipesCollection.document(id).set(recipe)
        return true
    }
    fun deleteRecipe(id: String): Boolean {
        recipesCollection.document(id).delete()
        return true
    }
}
