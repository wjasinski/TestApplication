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
            val receip = Recipe(title = receipObject.asJsonObject.get(TITLE).asString,
            description = receipObject.asJsonObject.get(DESCRIPTION).asString.replace("<br />", ""),
            imageUrl = parseImgUrl(receipObject.asJsonObject.get(IMAGES)),
            ingredientsNames = parseIngredientsNames(receipObject.asJsonObject.get(INGREDIENTS)))
            list.add(receip)
        }

        return list
    }

    private fun parseIngredientsNames(jsonElement: JsonElement): List<String> {
        val list = mutableListOf<String>()

        val ingredientsArray = jsonElement.asJsonArray

        for (ingredient in ingredientsArray) {
            list.add(ingredient.asJsonObject.get(INGREDIENT_NAME).asString)
        }

        return list
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