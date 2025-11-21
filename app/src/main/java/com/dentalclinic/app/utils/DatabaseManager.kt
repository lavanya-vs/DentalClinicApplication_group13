package com.dentalclinic.app.utils

import android.content.Context
import android.content.SharedPreferences
import com.dentalclinic.app.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DatabaseManager {
    private const val PREFS_NAME = "DentalClinicDB"
    private const val KEY_USERS = "users"
    private const val KEY_DOCTORS = "doctors"
    private const val KEY_PATIENTS = "patients"
    private const val KEY_APPOINTMENTS = "appointments"
    private const val KEY_BILLINGS = "billings"
    private const val KEY_CURRENT_USER = "current_user"

    private lateinit var prefs: SharedPreferences
    private val gson = Gson()

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        initializeSampleData()
    }

    // User Management
    fun saveCurrentUser(user: User) {
        prefs.edit().putString(KEY_CURRENT_USER, gson.toJson(user)).apply()
    }

    fun getCurrentUser(): User? {
        val json = prefs.getString(KEY_CURRENT_USER, null)
        return if (json != null) gson.fromJson(json, User::class.java) else null
    }

    fun logout() {
        prefs.edit().remove(KEY_CURRENT_USER).apply()
    }

    fun registerUser(user: User): Boolean {
        val users = getUsers().toMutableList()
        if (users.any { it.email == user.email }) return false
        users.add(user)
        saveUsers(users)
        return true
    }

    fun loginUser(email: String, password: String, type: String): User? {
        val users = getUsers()
        return users.find { it.email == email && it.password == password && it.type == type }
    }

    private fun getUsers(): List<User> {
        val json = prefs.getString(KEY_USERS, "[]")
        return gson.fromJson(json, object : TypeToken<List<User>>() {}.type)
    }

    private fun saveUsers(users: List<User>) {
        prefs.edit().putString(KEY_USERS, gson.toJson(users)).apply()
    }

    // Doctor Management
    fun getAllDoctors(): List<Doctor> {
        val json = prefs.getString(KEY_DOCTORS, "[]")
        return gson.fromJson(json, object : TypeToken<List<Doctor>>() {}.type)
    }

    fun getDoctorById(id: String): Doctor? {
        return getAllDoctors().find { it.id == id }
    }

    fun addDoctor(doctor: Doctor) {
        val doctors = getAllDoctors().toMutableList()
        doctors.add(doctor)
        prefs.edit().putString(KEY_DOCTORS, gson.toJson(doctors)).apply()
    }

    fun updateDoctor(doctor: Doctor) {
        val doctors = getAllDoctors().toMutableList()
        val index = doctors.indexOfFirst { it.id == doctor.id }
        if (index != -1) {
            doctors[index] = doctor
            prefs.edit().putString(KEY_DOCTORS, gson.toJson(doctors)).apply()
        }
    }

    fun deleteDoctor(id: String) {
        val doctors = getAllDoctors().toMutableList()
        doctors.removeAll { it.id == id }
        prefs.edit().putString(KEY_DOCTORS, gson.toJson(doctors)).apply()
    }

    // Patient Management
    fun getAllPatients(): List<Patient> {
        val json = prefs.getString(KEY_PATIENTS, "[]")
        return gson.fromJson(json, object : TypeToken<List<Patient>>() {}.type)
    }

    fun getPatientById(id: String): Patient? {
        return getAllPatients().find { it.id == id }
    }

    fun addPatient(patient: Patient) {
        val patients = getAllPatients().toMutableList()
        patients.add(patient)
        prefs.edit().putString(KEY_PATIENTS, gson.toJson(patients)).apply()
    }

    fun updatePatient(patient: Patient) {
        val patients = getAllPatients().toMutableList()
        val index = patients.indexOfFirst { it.id == patient.id }
        if (index != -1) {
            patients[index] = patient
            prefs.edit().putString(KEY_PATIENTS, gson.toJson(patients)).apply()
        }
    }

    fun deletePatient(id: String) {
        val patients = getAllPatients().toMutableList()
        patients.removeAll { it.id == id }
        prefs.edit().putString(KEY_PATIENTS, gson.toJson(patients)).apply()
    }

    // Appointment Management
    fun getAllAppointments(): List<Appointment> {
        val json = prefs.getString(KEY_APPOINTMENTS, "[]")
        return gson.fromJson(json, object : TypeToken<List<Appointment>>() {}.type)
    }

    fun getAppointmentsByDoctorId(doctorId: String): List<Appointment> {
        return getAllAppointments().filter { it.doctorId == doctorId }
    }

    fun getAppointmentsByPatientId(patientId: String): List<Appointment> {
        return getAllAppointments().filter { it.patientId == patientId }
    }

    fun addAppointment(appointment: Appointment) {
        val appointments = getAllAppointments().toMutableList()
        appointments.add(appointment)
        prefs.edit().putString(KEY_APPOINTMENTS, gson.toJson(appointments)).apply()
    }

    fun updateAppointment(appointment: Appointment) {
        val appointments = getAllAppointments().toMutableList()
        val index = appointments.indexOfFirst { it.id == appointment.id }
        if (index != -1) {
            appointments[index] = appointment
            prefs.edit().putString(KEY_APPOINTMENTS, gson.toJson(appointments)).apply()
        }
    }

    fun deleteAppointment(id: String) {
        val appointments = getAllAppointments().toMutableList()
        appointments.removeAll { it.id == id }
        prefs.edit().putString(KEY_APPOINTMENTS, gson.toJson(appointments)).apply()
    }

    // Billing Management
    fun getAllBillings(): List<Billing> {
        val json = prefs.getString(KEY_BILLINGS, "[]")
        return gson.fromJson(json, object : TypeToken<List<Billing>>() {}.type)
    }

    fun getBillingsByDoctorId(doctorId: String): List<Billing> {
        return getAllBillings().filter { it.doctorId == doctorId }
    }

    fun getBillingsByPatientId(patientId: String): List<Billing> {
        return getAllBillings().filter { it.patientId == patientId }
    }

    fun addBilling(billing: Billing) {
        val billings = getAllBillings().toMutableList()
        billings.add(billing)
        prefs.edit().putString(KEY_BILLINGS, gson.toJson(billings)).apply()
    }

    fun updateBilling(billing: Billing) {
        val billings = getAllBillings().toMutableList()
        val index = billings.indexOfFirst { it.id == billing.id }
        if (index != -1) {
            billings[index] = billing
            prefs.edit().putString(KEY_BILLINGS, gson.toJson(billings)).apply()
        }
    }

    fun deleteBilling(id: String) {
        val billings = getAllBillings().toMutableList()
        billings.removeAll { it.id == id }
        prefs.edit().putString(KEY_BILLINGS, gson.toJson(billings)).apply()
    }

    // Initialize sample data
    private fun initializeSampleData() {
        if (getAllDoctors().isEmpty()) {
            val sampleDoctors = listOf(
                Doctor("doc1", "Dr. Sarah Johnson", "Orthodontist", "sarah@dental.com", "123-456-7890", 4.8f, "Mon-Fri: 9AM-5PM", 150.0),
                Doctor("doc2", "Dr. Michael Chen", "Endodontist", "michael@dental.com", "123-456-7891", 4.9f, "Tue-Sat: 10AM-6PM", 200.0),
                Doctor("doc3", "Dr. Emily Brown", "Periodontist", "emily@dental.com", "123-456-7892", 4.7f, "Mon-Thu: 8AM-4PM", 180.0)
            )
            sampleDoctors.forEach { addDoctor(it) }

            val sampleUsers = listOf(
                User("doc1", "sarah@dental.com", "doc123", "Dr. Sarah Johnson", "doctor"),
                User("doc2", "michael@dental.com", "doc123", "Dr. Michael Chen", "doctor"),
                User("doc3", "emily@dental.com", "doc123", "Dr. Emily Brown", "doctor"),
                User("pat1", "patient@test.com", "pat123", "John Doe", "patient")
            )
            sampleUsers.forEach { registerUser(it) }
        }
    }
}