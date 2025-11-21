package com.dentalclinic.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dentalclinic.app.R
import com.dentalclinic.app.utils.DatabaseManager

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var userTypeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        DatabaseManager.init(this)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)
        userTypeSpinner = findViewById(R.id.userTypeSpinner)

        setupSpinner()

        loginButton.setOnClickListener {
            handleLogin()
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setupSpinner() {
        val userTypes = arrayOf("Doctor", "Patient")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userTypeSpinner.adapter = adapter
    }

    private fun handleLogin() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()
        val userType = if (userTypeSpinner.selectedItemPosition == 0) "doctor" else "patient"

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val user = DatabaseManager.loginUser(email, password, userType)
        if (user != null) {
            DatabaseManager.saveCurrentUser(user)
            val intent = if (userType == "doctor") {
                Intent(this, DoctorDashboardActivity::class.java)
            } else {
                Intent(this, PatientDashboardActivity::class.java)
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }
}