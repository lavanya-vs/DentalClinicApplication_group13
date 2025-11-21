package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Doctor

class PatientDoctorAdapter(
    private val doctors: List<Doctor>,
    private val onBook: (Doctor) -> Unit
) : RecyclerView.Adapter<PatientDoctorAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorName: TextView = view.findViewById(R.id.doctorName)
        val specialization: TextView = view.findViewById(R.id.specialization)
        val rating: RatingBar = view.findViewById(R.id.rating)
        val availableTime: TextView = view.findViewById(R.id.availableTime)
        val charge: TextView = view.findViewById(R.id.charge)
        val bookButton: Button = view.findViewById(R.id.bookButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient_doctor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctors[position]

        holder.doctorName.text = doctor.name
        holder.specialization.text = doctor.specialization
        holder.rating.rating = doctor.rating
        holder.availableTime.text = doctor.availableTime
        holder.charge.text = "$${"%.2f".format(doctor.chargePerVisit)} per visit"

        holder.bookButton.setOnClickListener {
            onBook(doctor)
        }
    }

    override fun getItemCount() = doctors.size
}