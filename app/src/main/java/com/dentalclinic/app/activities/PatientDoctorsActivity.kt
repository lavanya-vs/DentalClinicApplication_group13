package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.PatientDoctorAdapter
import com.dentalclinic.app.models.Appointment
import com.dentalclinic.app.models.Billing
import com.dentalclinic.app.utils.DatabaseManager
import java.text.SimpleDateFormat
import java.util.*

class PatientDoctorsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_doctors)

        recyclerView = findViewById(R.id.doctorsRecyclerView)
        backButton = findViewById(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadDoctors()

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadDoctors() {
        val doctors = DatabaseManager.getAllDoctors()
        val adapter = PatientDoctorAdapter(doctors) { doctor ->
            showBookingDialog(doctor.id, doctor.name, doctor.chargePerVisit)
        }
        recyclerView.adapter = adapter
    }

    private fun showBookingDialog(doctorId: String, doctorName: String, charge: Double) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_book_appointment, null)
        val dateInput = dialogView.findViewById<EditText>(R.id.dateInput)
        val timeInput = dialogView.findViewById<EditText>(R.id.timeInput)
        val reasonInput = dialogView.findViewById<EditText>(R.id.reasonInput)

        AlertDialog.Builder(this)
            .setTitle("Book Appointment")
            .setView(dialogView)
            .setPositiveButton("Book") { _, _ ->
                bookAppointment(doctorId, doctorName, dateInput.text.toString(),
                    timeInput.text.toString(), reasonInput.text.toString(), charge)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun bookAppointment(doctorId: String, doctorName: String,
                                date: String, time: String, reason: String, charge: Double) {
        val currentUser = DatabaseManager.getCurrentUser() ?: return
        val patient = DatabaseManager.getPatientById(currentUser.id)

        val appointment = Appointment(
            id = UUID.randomUUID().toString(),
            doctorId = doctorId,
            doctorName = doctorName,
            patientId = currentUser.id,
            patientName = currentUser.name,
            date = date,
            time = time,
            status = "pending",
            reason = reason,
            patientIssue = patient?.currentIssue ?: "",
            patientHistory = patient?.medicalHistory ?: ""
        )
        DatabaseManager.addAppointment(appointment)

        val billing = Billing(
            id = UUID.randomUUID().toString(),
            appointmentId = appointment.id,
            doctorId = doctorId,
            doctorName = doctorName,
            patientId = currentUser.id,
            patientName = currentUser.name,
            amount = charge,
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
            paymentStatus = "pending",
            description = reason
        )
        DatabaseManager.addBilling(billing)

        Toast.makeText(this, "Appointment booked successfully!", Toast.LENGTH_SHORT).show()
    }
}
