package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.DoctorPatientAdapter
import com.dentalclinic.app.models.Patient
import com.dentalclinic.app.utils.DatabaseManager

class DoctorPatientsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_patients)

        recyclerView = findViewById(R.id.patientsRecyclerView)
        backButton = findViewById(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadPatients()

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadPatients() {
        val currentUser = DatabaseManager.getCurrentUser()
        val appointments = DatabaseManager.getAppointmentsByDoctorId(currentUser?.id ?: "")
        val patientIds = appointments.map { it.patientId }.distinct()
        val patients = DatabaseManager.getAllPatients().filter { it.id in patientIds }

        val adapter = DoctorPatientAdapter(patients) { patient ->
            showPatientDetails(patient)
        }
        recyclerView.adapter = adapter
    }

    private fun showPatientDetails(patient: Patient) {
        AlertDialog.Builder(this)
            .setTitle("Patient Details")
            .setMessage("""
                Name: ${patient.name}
                Email: ${patient.email}
                Phone: ${patient.phone}
                Date of Birth: ${patient.dateOfBirth}
                Address: ${patient.address}
                
                Medical History:
                ${patient.medicalHistory}
                
                Last Visit: ${patient.lastVisit}
                Current Issue: ${patient.currentIssue}
            """.trimIndent())
            .setPositiveButton("Close", null)
            .show()
    }
}
