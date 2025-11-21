// FILE: activities/PatientDashboardActivity.kt
package com.dentalclinic.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.dentalclinic.app.R
import com.dentalclinic.app.utils.DatabaseManager

class PatientDashboardActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var doctorsCard: CardView
    private lateinit var appointmentsCard: CardView
    private lateinit var billingCard: CardView
    private lateinit var dentalGuideCard: CardView
    private lateinit var addPatientCard: CardView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_dashboard)

        welcomeText = findViewById(R.id.welcomeText)
        doctorsCard = findViewById(R.id.doctorsCard)
        appointmentsCard = findViewById(R.id.appointmentsCard)
        billingCard = findViewById(R.id.billingCard)
        dentalGuideCard = findViewById(R.id.dentalGuideCard)
        addPatientCard = findViewById(R.id.addPatientCard)
        logoutButton = findViewById(R.id.logoutButton)

        val currentUser = DatabaseManager.getCurrentUser()
//      welcomeText.text = "Welcome, ${currentUser?.name}"
        welcomeText.text = "Welcome, ${currentUser?.name ?: "Patient"}"

        doctorsCard.setOnClickListener {
            startActivity(Intent(this, PatientDoctorsActivity::class.java))
        }

        appointmentsCard.setOnClickListener {
            startActivity(Intent(this, PatientAppointmentsActivity::class.java))
        }

        billingCard.setOnClickListener {
            startActivity(Intent(this, PatientBillingActivity::class.java))
        }

        dentalGuideCard.setOnClickListener {
            startActivity(Intent(this, DentalGuideActivity::class.java))
        }

        addPatientCard.setOnClickListener {
            startActivity(Intent(this, AddPatientActivity::class.java))
        }

        logoutButton.setOnClickListener {
            DatabaseManager.logout()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh when returning to dashboard
    }
}
