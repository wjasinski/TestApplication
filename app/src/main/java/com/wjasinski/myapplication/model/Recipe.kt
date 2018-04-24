package com.wjasinski.myapplication.model

import java.lang.StringBuilder

data class Recipe(
        val title: String? = null,
        val imageUrl: String? = null,
        val description: String? = null,
        val ingredientsNames: List<String>? = null
) {
    fun getIngredientsAsString() : String {
        if (ingredientsNames != null) {
            val stringBuilder = StringBuilder()
            stringBuilder.append("Ingredients: ")//TODO move it to strings
            for (ingredient in ingredientsNames!!) {
                if (!ingredient.isNullOrEmpty()) {
                    stringBuilder.append(ingredient)
                    stringBuilder.append(", ")
                }
            }
            return stringBuilder.toString()
        } else {
            return ""
        }
    }
}