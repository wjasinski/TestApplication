package com.wjasinski.myapplication.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecipeDeserializer : JsonDeserializer<List<Recipe>> {
    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGES = "images"
        const val IMG_URL = "url"
        const val INGREDIENTS = "ingredients"
        const val INGREDIENT_NAME = "name"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): List<Recipe> {
        val list = mutableListOf<Recipe>()

        val mainArray = json.asJsonArray

        for (receipObject in mainArray) {
            val recipe = Recipe(title = receipObject.asJsonObject.get(TITLE).asString,
            description = receipObject.asJsonObject.get(DESCRIPTION).asString.replace("<br />", ""),
            imageUrl = parseImgUrl(receipObject.asJsonObject.get(IMAGES)),
            ingredientsNames = parseIngredientsNames(receipObject.asJsonObject.get(INGREDIENTS)))
            list.add(recipe)
        }

        return list
    }

    private fun parseIngredientsNames(jsonElement: JsonElement): String {
        val stringBuilder = StringBuilder()
        val ingredientsArray = jsonElement.asJsonArray

        stringBuilder.append("Ingredients: ")//TODO move to strings

        for (ingredient in ingredientsArray) {
            val string = ingredient.asJsonObject.get(INGREDIENT_NAME).asString
            if(!string.isNullOrEmpty()) {
                stringBuilder.append(string)
                stringBuilder.append(", ")
            }
        }
        stringBuilder.setLength(Math.max(stringBuilder.length - 2, 0)) //removing last ","

        return stringBuilder.toString()
    }

    private fun parseImgUrl(jsonElement: JsonElement): String {
        var imgUrl = ""
        val imagesArray = jsonElement.asJsonArray

        for (image in imagesArray) {
            imgUrl = image.asJsonObject.get(IMG_URL).asString
        }

        return imgUrl
    }
}