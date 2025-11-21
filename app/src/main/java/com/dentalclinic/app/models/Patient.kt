package com.dentalclinic.app.models

data class Patient(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val dateOfBirth: String,
    val address: String,
    val medicalHistory: String,
    val lastVisit: String,
    val currentIssue: String
)