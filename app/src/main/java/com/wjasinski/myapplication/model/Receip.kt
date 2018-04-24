package com.wjasinski.myapplication.model

data class Receip(
        var title: String? = null,
        var imageUrl: String? = null,
        var description: String? = null,
        var ingredientsNames: List<String>? = null
)