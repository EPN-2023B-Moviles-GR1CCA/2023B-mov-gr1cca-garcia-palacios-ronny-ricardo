package org.example

import org.example.models.Ingredient
import org.example.models.Recipe
import org.example.reprositories.RIngredient
import org.example.reprositories.RRecipe
import java.util.UUID

fun main() {
    println("---------------------------------------------------------------")
    println("Deber 1 - Kotlin")
    println("Este programa es un ejemplo de crud con Kotlin para los modelos Recipe e Ingredient")
    println("---------------------------------------------------------------")
    while (true) {
        println()
        println("Seleccione el modelo a gestionar")
        println("1. Recipe")
        println("2. Ingredient")
        println("3. Exit")
        println()
        when (readlnOrNull()?.toIntOrNull() ?: 0) {
            1 -> {
                manageRecipe()
            }

            2 -> {
                manageIngredient()
            }

            3 -> {
                manageExit()
                break
            }

            else -> {
                println("Opción no válida")
            }
        }

    }
}

fun printOptionsRecipe() { //CRUD
    println()
    println("1. Crear receta")
    println("2. Listar recetas")
    println("3. Actualizar receta")
    println("4. Eliminar receta")
    println("5. Regresar")
}

fun printOptionsIngredient() { //CRUD
    println()
    println("1. Crear ingrediente")
    println("2. Listar ingredientes")
    println("3. Actualizar ingrediente")
    println("4. Eliminar ingrediente")
    println("5. Regresar")
}

fun manageRecipe() {
    println("Gestionando Recipe")
    printOptionsRecipe()
    when (readlnOrNull()?.toIntOrNull() ?: 0) {
        1 -> {
            println("Creando receta")
            print("Ingrese el nombre de la receta: ")
            val name = readlnOrNull() ?: return println("Nombre no válido")
            print("Ingrese la descripción de la receta: ")
            val description = readlnOrNull() ?: return println("Descripción no válida")
            print("Cuantos ingredientes contiene la receta: ")
            val numberOfIngredients = readlnOrNull()?.toIntOrNull() ?: return println("Cantidad no válida")
            if (numberOfIngredients < 0 || numberOfIngredients > 3) return println("Cantidad no válida, debe ser entre 0 y 3")
            val ingredients = mutableListOf<Ingredient>()
            for (i in 1..numberOfIngredients) {
                print("Ingrese el id del ingrediente $i: ")
                val id = readlnOrNull() ?: return println("Id no válido")
                val ingredient = RIngredient.getIngredient(id) ?: return println("No existe el ingrediente")
                ingredients.add(ingredient)
            }
            val uuid = UUID.randomUUID()
            val recipe = Recipe(uuid.toString(), name, description, ingredients)
            val flag = RRecipe.createRecipe(recipe)
            if (flag) println("Receta creada con éxito") else println("No se pudo crear la receta")
        }

        2 -> {
            println("Listando recetas")
            val recipes = RRecipe.getRecipes()
            if (recipes.isEmpty()) return println("No hay recetas")
            recipes.forEach {
                println(it)
            }
        }

        3 -> {
            println("Actualizando receta")
            print("Ingrese el id de la receta: ")
            val id = readlnOrNull() ?: return println("Id no válido")
            val recipe = RRecipe.getRecipe(id) ?: return println("No existe la receta")
            println("Receta encontrada: $recipe")
            print("Ingrese el nombre de la receta: ")
            val name = readlnOrNull() ?: return println("Nombre no válido")
            print("Ingrese la descripción de la receta: ")
            val description = readlnOrNull() ?: return println("Descripción no válida")
            print("Cuantos ingredientes contiene la receta: ")
            val numberOfIngredients = readlnOrNull()?.toIntOrNull() ?: return println("Cantidad no válida")
            if (numberOfIngredients < 0 || numberOfIngredients > 3) return println("Cantidad no válida, debe ser entre 0 y 3")
            val ingredients = mutableListOf<Ingredient>()
            for (i in 1..numberOfIngredients) {
                print("Ingrese el id del ingrediente $i: ")
                val idIngredient = readlnOrNull() ?: return println("Id no válido")
                val ingredient = RIngredient.getIngredient(idIngredient) ?: return println("No existe el ingrediente")
                ingredients.add(ingredient)
            }
            val newRecipe = Recipe(id, name, description, ingredients)
            val flag = RRecipe.updateRecipe(newRecipe)
            if (flag) println("Receta actualizada con éxito") else println("No se pudo actualizar la receta")
        }

        4 -> {
            println("Eliminando receta")
            print("Ingrese el id de la receta: ")
            val id = readlnOrNull() ?: return println("Id no válido")
            val flag = RRecipe.deleteRecipe(id)
            if (flag) println("Receta eliminada con éxito") else println("No se pudo eliminar la receta")
        }

        5 -> {
            println("Regresando...")
        }
        else -> {
            println("Opción no válida, regresando...")
        }
    }
}

fun manageIngredient() {
    println("Gestionando Ingredient")
    printOptionsIngredient()
    when (readlnOrNull()?.toIntOrNull() ?: 0) {
        1 -> {
            println("Creando ingrediente")
            print("Ingrese el nombre del ingrediente: ")
            val name = readlnOrNull() ?: return println("Nombre no válido")
            print("Ingrese la cantidad del ingrediente: ")
            val quantity = readlnOrNull()?.toIntOrNull() ?: return println("Cantidad no válida")
            print("Ingrese la unidad del ingrediente: ")
            val unit = readlnOrNull() ?: return println("Unidad no válida")
            val uuid = UUID.randomUUID()
            val ingredient = Ingredient(uuid.toString(), name, quantity, unit)
            val flag = RIngredient.createIngredient(ingredient)
            if (flag) println("Ingrediente creado con éxito") else println("No se pudo crear el ingrediente")
        }

        2 -> {
            println("Listando ingredientes:")
            val ingredients = RIngredient.getIngredients()
            if (ingredients.isEmpty()) return println("No hay ingredientes")
            ingredients.forEach {
                println(it)
            }
        }

        3 -> {
            println("Actualizando ingrediente")
            print("Ingrese el id del ingrediente: ")
            val id = readlnOrNull() ?: return println("Id no válido")
            val ingredient = RIngredient.getIngredient(id) ?: return println("No existe el ingrediente")
            println("Ingrediente encontrado: $ingredient")
            print("Ingrese el nombre del ingrediente: ")
            val name = readlnOrNull() ?: return println("Nombre no válido")
            print("Ingrese la cantidad del ingrediente: ")
            val quantity = readlnOrNull()?.toIntOrNull() ?: return println("Cantidad no válida")
            print("Ingrese la unidad del ingrediente: ")
            val unit = readlnOrNull() ?: return println("Unidad no válida")
            val newIngredient = Ingredient(id, name, quantity, unit)
            val flag = RIngredient.updateIngredient(newIngredient)
            if (flag) println("Ingrediente actualizado con éxito") else println("No se pudo actualizar el ingrediente")
        }
        4 -> {
            println("Eliminando ingrediente")
            print("Ingrese el id del ingrediente: ")
            val id = readlnOrNull() ?: return println("Id no válido")
            val flag = RIngredient.deleteIngredient(id)
            if (flag) println("Ingrediente eliminado con éxito") else println("No se pudo eliminar el ingrediente")
        }
        5 -> {
            println("Regresando...")
        }
        else -> {
            println("Opción no válida, regresando...")
        }
    }
}

fun manageExit() {
    println("Saliendo..., gracias por usar el programa")
}