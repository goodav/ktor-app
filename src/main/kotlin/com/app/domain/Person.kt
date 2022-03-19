package com.app.domain

data class Person(
    val _id: String? = null,
    val id: Long,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: Gender,
    val isFunny: Boolean?
)
