package com.wjasinski.myapplication.model

import java.lang.StringBuilder

data class Receip(
        var title: String? = null,
        var imageUrl: String? = null,
        var description: String? = null,
        var ingredientsNames: List<String>? = null
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