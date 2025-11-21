package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Patient
import com.dentalclinic.app.utils.DatabaseManager
import java.util.*

class AddPatientActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var dobInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var medicalHistoryInput: EditText
    private lateinit var lastVisitInput: EditText
    private lateinit var currentIssueInput: EditText
    private lateinit var saveButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        phoneInput = findViewById(R.id.phoneInput)
        dobInput = findViewById(R.id.dobInput)
        addressInput = findViewById(R.id.addressInput)
        medicalHistoryInput = findViewById(R.id.medicalHistoryInput)
        lastVisitInput = findViewById(R.id.lastVisitInput)
        currentIssueInput = findViewById(R.id.currentIssueInput)
        saveButton = findViewById(R.id.saveButton)
        backButton = findViewById(R.id.backButton)

        saveButton.setOnClickListener {
            savePatient()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun savePatient() {
        val name = nameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val phone = phoneInput.text.toString().trim()
        val dob = dobInput.text.toString().trim()
        val address = addressInput.text.toString().trim()
        val medicalHistory = medicalHistoryInput.text.toString().trim()
        val lastVisit = lastVisitInput.text.toString().trim()
        val currentIssue = currentIssueInput.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill required fields (Name, Email, Phone)", Toast.LENGTH_SHORT).show()
            return
        }

        val patient = Patient(
            id = UUID.randomUUID().toString(),
            name = name,
            email = email,
            phone = phone,
            dateOfBirth = dob,
            address = address,
            medicalHistory = medicalHistory,
            lastVisit = lastVisit,
            currentIssue = currentIssue
        )

        DatabaseManager.addPatient(patient)
        Toast.makeText(this, "Patient added successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }
}
