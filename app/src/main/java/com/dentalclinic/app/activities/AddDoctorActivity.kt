package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Doctor
import com.dentalclinic.app.utils.DatabaseManager
import java.util.*

class AddDoctorActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var specializationInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var ratingInput: EditText
    private lateinit var availableTimeInput: EditText
    private lateinit var chargeInput: EditText
    private lateinit var saveButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doctor)

        nameInput = findViewById(R.id.nameInput)
        specializationInput = findViewById(R.id.specializationInput)
        emailInput = findViewById(R.id.emailInput)
        phoneInput = findViewById(R.id.phoneInput)
        ratingInput = findViewById(R.id.ratingInput)
        availableTimeInput = findViewById(R.id.availableTimeInput)
        chargeInput = findViewById(R.id.chargeInput)
        saveButton = findViewById(R.id.saveButton)
        backButton = findViewById(R.id.backButton)

        saveButton.setOnClickListener {
            saveDoctor()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun saveDoctor() {
        val name = nameInput.text.toString().trim()
        val specialization = specializationInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val phone = phoneInput.text.toString().trim()
        val ratingStr = ratingInput.text.toString().trim()
        val availableTime = availableTimeInput.text.toString().trim()
        val chargeStr = chargeInput.text.toString().trim()

        if (name.isEmpty() || specialization.isEmpty() || email.isEmpty() ||
            phone.isEmpty() || ratingStr.isEmpty() || availableTime.isEmpty() || chargeStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val rating = ratingStr.toFloatOrNull() ?: 0f
        val charge = chargeStr.toDoubleOrNull() ?: 0.0

        if (rating < 0 || rating > 5) {
            Toast.makeText(this, "Rating must be between 0 and 5", Toast.LENGTH_SHORT).show()
            return
        }

        val doctor = Doctor(
            id = UUID.randomUUID().toString(),
            name = name,
            specialization = specialization,
            email = email,
            phone = phone,
            rating = rating,
            availableTime = availableTime,
            chargePerVisit = charge
        )

        DatabaseManager.addDoctor(doctor)
        Toast.makeText(this, "Doctor added successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
