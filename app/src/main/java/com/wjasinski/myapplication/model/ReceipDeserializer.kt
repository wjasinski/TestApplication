package com.wjasinski.myapplication.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ReceipDeserializer : JsonDeserializer<List<Receip>> {
    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGES = "images"
        const val IMG_URL = "url"
        const val INGREDIENTS = "ingredients"
        const val INGREDIENT_NAME = "name"
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): List<Receip> {
        val list = mutableListOf<Receip>()

        val mainArray = json.asJsonArray

        for (receipObject in mainArray) {
            val receip = Receip()
            receip.title = receipObject.asJsonObject.get(TITLE).asString
            receip.description = receipObject.asJsonObject.get(DESCRIPTION).asString
            receip.imageUrl = parseImgUrl(receipObject.asJsonObject.get(IMAGES))
            receip.ingredientsNames = parseIngredientsNames(receipObject.asJsonObject.get(INGREDIENTS))
            list.add(receip)
        }

        return list
    }

    private fun parseIngredientsNames(jsonElement: JsonElement): List<String>? {
        val list = mutableListOf<String>()

        val ingredientsArray = jsonElement.asJsonArray

        for (ingredient in ingredientsArray) {
            list.add(ingredient.asJsonObject.get(INGREDIENT_NAME).asString)
        }

        return list
    }

    private fun parseImgUrl(jsonElement: JsonElement): String? {
        var imgUrl = ""
        val imagesArray = jsonElement.asJsonArray

        for (image in imagesArray) {
            imgUrl = image.asJsonObject.get(IMG_URL).asString
        }

        return imgUrl
    }
}