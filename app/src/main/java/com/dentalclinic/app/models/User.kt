package com.dentalclinic.app.models

data class User(
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val type: String // "doctor" or "patient"
)