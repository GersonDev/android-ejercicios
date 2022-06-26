package com.example.columnapplication.domain.models

import com.example.columnapplication.domain.models.Card

data class Person(
    val name: String,
    val apellido: String,
    val dni: Int,
    var card: Card? = null
)