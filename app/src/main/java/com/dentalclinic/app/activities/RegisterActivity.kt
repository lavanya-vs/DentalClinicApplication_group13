package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dentalclinic.app.R
import com.dentalclinic.app.models.*
import com.dentalclinic.app.utils.DatabaseManager
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var registerButton: Button
    private lateinit var backButton: Button
    private lateinit var userTypeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameInput = findViewById(R.id.nameInput)
        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        registerButton = findViewById(R.id.registerButton)
        backButton = findViewById(R.id.backButton)
        userTypeSpinner = findViewById(R.id.userTypeSpinner)

        setupSpinner()

        registerButton.setOnClickListener {
            handleRegister()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupSpinner() {
        val userTypes = arrayOf("Doctor", "Patient")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userTypeSpinner.adapter = adapter
    }

    private fun handleRegister() {
        val name = nameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()
        val userType = if (userTypeSpinner.selectedItemPosition == 0) "doctor" else "patient"

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val userId = UUID.randomUUID().toString()
        val user = User(userId, email, password, name, userType)

        if (DatabaseManager.registerUser(user)) {
            if (userType == "patient") {
                val patient = Patient(
                    userId, name, email, "", "", "", "", "", ""
                )
                DatabaseManager.addPatient(patient)
            }
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
        }
    }
}
