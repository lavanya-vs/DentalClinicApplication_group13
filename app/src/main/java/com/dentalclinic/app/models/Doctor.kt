package com.dentalclinic.app.models

data class Doctor(
    val id: String,
    val name: String,
    val specialization: String,
    val email: String,
    val phone: String,
    val rating: Float,
    val availableTime: String,
    val chargePerVisit: Double
)