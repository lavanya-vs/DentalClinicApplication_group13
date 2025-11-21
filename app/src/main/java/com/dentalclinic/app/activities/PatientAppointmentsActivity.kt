package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.PatientAppointmentAdapter
import com.dentalclinic.app.utils.DatabaseManager

class PatientAppointmentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_appointments)

        recyclerView = findViewById(R.id.appointmentsRecyclerView)
        backButton = findViewById(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadAppointments()

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        loadAppointments()
    }

    private fun loadAppointments() {
        val currentUser = DatabaseManager.getCurrentUser()
        val appointments = DatabaseManager.getAppointmentsByPatientId(currentUser?.id ?: "")

        val adapter = PatientAppointmentAdapter(appointments)
        recyclerView.adapter = adapter
    }
}
