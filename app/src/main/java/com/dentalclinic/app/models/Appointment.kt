package com.dentalclinic.app.models

data class Appointment(
    val id: String,
    var doctorId: String,
    var doctorName: String,
    val patientId: String,
    val patientName: String,
    val date: String,
    val time: String,
    var status: String, // "pending", "accepted", "completed", "cancelled"
    val reason: String,
    val patientIssue: String,
    val patientHistory: String
)