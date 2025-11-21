package com.dentalclinic.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.dentalclinic.app.R
import com.dentalclinic.app.utils.DatabaseManager

class DoctorDashboardActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var appointmentsCard: CardView
    private lateinit var patientsCard: CardView
    private lateinit var billingCard: CardView
    private lateinit var addDoctorCard: CardView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_dashboard)

        welcomeText = findViewById(R.id.welcomeText)
        appointmentsCard = findViewById(R.id.appointmentsCard)
        patientsCard = findViewById(R.id.patientsCard)
        billingCard = findViewById(R.id.billingCard)
        addDoctorCard = findViewById(R.id.addDoctorCard)
        logoutButton = findViewById(R.id.logoutButton)

        val currentUser = DatabaseManager.getCurrentUser()
//        welcomeText.text = "Welcome, ${currentUser?.name}"
        welcomeText.text = "Welcome, ${currentUser?.name ?: "Doctor"}"


        appointmentsCard.setOnClickListener {
            startActivity(Intent(this, DoctorAppointmentsActivity::class.java))
        }

        patientsCard.setOnClickListener {
            startActivity(Intent(this, DoctorPatientsActivity::class.java))
        }

        billingCard.setOnClickListener {
            startActivity(Intent(this, DoctorBillingActivity::class.java))
        }

        addDoctorCard.setOnClickListener {
            startActivity(Intent(this, AddDoctorActivity::class.java))
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
