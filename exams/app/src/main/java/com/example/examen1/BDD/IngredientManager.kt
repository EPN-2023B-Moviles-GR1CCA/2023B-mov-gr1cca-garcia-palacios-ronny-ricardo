package com.example.examen1.BDD

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase
import org.example.models.Ingredient

class IngredientManager {
//    private val database: DatabaseReference = Firebase.database.reference.child("ingredients")
    private val db = FirebaseFirestore.getInstance()
    private val ingredientsCollection = db.collection("ingredients")

    fun createIngredient(id: String, name: String, quantity: Long, unit: String): Boolean {
        val ingredient = hashMapOf(
            "id" to id,
            "name" to name,
            "quantity" to quantity,
            "unit" to unit
        )
        ingredientsCollection.document(id).set(ingredient)
        return true
    }

    fun readIngredients(): Task<QuerySnapshot> {
//        val ingredients = arrayListOf<Ingredient>()
        return ingredientsCollection.get();
//            .addOnSuccessListener { result ->
//            for (document in result) {
//                Log.i("docIngredient", "document.data: ${document.data}")
//                val ingredient = Ingredient(
//                    document.id,
//                    document.data["name"] as String,
//                    (document.data["quantity"] as Long).toInt(),
//                    document.data["unit"] as String
//                )
//                ingredients.add(ingredient)
//            }
//        }
//        Log.i("IngredientsArray", "ingredients: $ingredients")
//        return ingredients
    }

    fun readIngredient(id: String): Task<DocumentSnapshot> {
//        var ingredient: Ingredient? = null
        return ingredientsCollection.document(id).get()
//        .addOnSuccessListener { result ->
//            ingredient = Ingredient(
//                result.id,
//                result.data!!["name"] as String,
//                (result.data!!["quantity"] as Long).toInt(),
//                result.data!!["unit"] as String
//            )
//        }
//        return ingredient
    }

    fun uploadIngredient(id: String, name: String, quantity: Long, unit: String): Boolean {
        val ingredient = hashMapOf(
            "id" to id,
            "name" to name,
            "quantity" to quantity,
            "unit" to unit
        )
        ingredientsCollection.document(id).set(ingredient)
        return true
    }

    fun deleteIngredient(id: String): Boolean {
        ingredientsCollection.document(id).delete()
        return true
    }
}
