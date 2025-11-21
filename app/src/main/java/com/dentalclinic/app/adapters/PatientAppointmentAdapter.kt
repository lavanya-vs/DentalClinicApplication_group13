package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Appointment

class PatientAppointmentAdapter(
    private val appointments: List<Appointment>
) : RecyclerView.Adapter<PatientAppointmentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorName: TextView = view.findViewById(R.id.doctorName)
        val dateTime: TextView = view.findViewById(R.id.dateTime)
        val reason: TextView = view.findViewById(R.id.reason)
        val statusBadge: TextView = view.findViewById(R.id.statusBadge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient_appointment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]

        holder.doctorName.text = appointment.doctorName
        holder.dateTime.text = "${appointment.date} at ${appointment.time}"
        holder.reason.text = appointment.reason

        when (appointment.status) {
            "accepted" -> {
                holder.statusBadge.text = "Accepted"
                holder.statusBadge.setBackgroundResource(R.drawable.accepted_background)
            }
            "completed" -> {
                holder.statusBadge.text = "Completed"
                holder.statusBadge.setBackgroundResource(R.drawable.accepted_background)
            }
            else -> {
                holder.statusBadge.text = "Pending"
                holder.statusBadge.setBackgroundResource(R.drawable.pending_background)
            }
        }
    }

    override fun getItemCount() = appointments.size
}