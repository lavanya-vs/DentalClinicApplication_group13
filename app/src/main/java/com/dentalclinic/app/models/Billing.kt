package com.dentalclinic.app.models

data class Billing(
    val id: String,
    val appointmentId: String,
    val doctorId: String,
    val doctorName: String,
    val patientId: String,
    val patientName: String,
    val amount: Double,
    val date: String,
    var paymentStatus: String, // "pending", "paid"
    val description: String
)