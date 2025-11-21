package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Patient

class DoctorPatientAdapter(
    private val patients: List<Patient>,
    private val onPatientClick: (Patient) -> Unit
) : RecyclerView.Adapter<DoctorPatientAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val patientName: TextView = view.findViewById(R.id.patientName)
        val lastVisit: TextView = view.findViewById(R.id.lastVisit)
        val currentIssue: TextView = view.findViewById(R.id.currentIssue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor_patient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = patients[position]

        holder.patientName.text = patient.name
        holder.lastVisit.text = "Last Visit: ${patient.lastVisit.ifEmpty { "First visit" }}"
        holder.currentIssue.text = patient.currentIssue.ifEmpty { "No current issues" }

        holder.itemView.setOnClickListener {
            onPatientClick(patient)
        }
    }

    override fun getItemCount() = patients.size
}