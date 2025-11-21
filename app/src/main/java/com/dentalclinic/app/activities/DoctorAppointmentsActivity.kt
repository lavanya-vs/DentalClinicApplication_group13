package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.DoctorAppointmentAdapter
import com.dentalclinic.app.models.Appointment
import com.dentalclinic.app.utils.DatabaseManager

class DoctorAppointmentsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button
    private lateinit var adapter: DoctorAppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_appointments)

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
        val appointments = DatabaseManager.getAppointmentsByDoctorId(currentUser?.id ?: "")

        adapter = DoctorAppointmentAdapter(
            appointments.toMutableList(),
            onAccept = { appointment -> acceptAppointment(appointment) },
            onViewHistory = { appointment -> viewPatientHistory(appointment) }
        )
        recyclerView.adapter = adapter
    }

    private fun acceptAppointment(appointment: Appointment) {
        appointment.status = "accepted"
        DatabaseManager.updateAppointment(appointment)
        loadAppointments()
        Toast.makeText(this, "Appointment accepted", Toast.LENGTH_SHORT).show()
    }

    private fun viewPatientHistory(appointment: Appointment) {
        val patient = DatabaseManager.getPatientById(appointment.patientId)

        AlertDialog.Builder(this)
            .setTitle("Patient History")
            .setMessage("""
                Patient: ${appointment.patientName}
                Last Visit: ${patient?.lastVisit ?: "First visit"}
                Medical History: ${patient?.medicalHistory ?: "None"}
                Current Issue: ${appointment.patientIssue}
            """.trimIndent())
            .setPositiveButton("Close", null)
            .show()
    }
}