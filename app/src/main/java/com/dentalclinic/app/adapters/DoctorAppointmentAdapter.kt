package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Appointment

class DoctorAppointmentAdapter(
    private val appointments: MutableList<Appointment>,
    private val onAccept: (Appointment) -> Unit,
    private val onViewHistory: (Appointment) -> Unit
) : RecyclerView.Adapter<DoctorAppointmentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val patientName: TextView = view.findViewById(R.id.patientName)
        val dateTime: TextView = view.findViewById(R.id.dateTime)
        val reason: TextView = view.findViewById(R.id.reason)
        val statusBadge: TextView = view.findViewById(R.id.statusBadge)
        val acceptButton: Button = view.findViewById(R.id.acceptButton)
        val viewHistoryButton: Button = view.findViewById(R.id.viewHistoryButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor_appointment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]

        holder.patientName.text = appointment.patientName
        holder.dateTime.text = "${appointment.date} at ${appointment.time}"
        holder.reason.text = appointment.reason

        if (appointment.status == "accepted") {
            holder.statusBadge.text = "Accepted"
            holder.statusBadge.setBackgroundResource(R.drawable.accepted_background)
            holder.acceptButton.visibility = View.GONE
        } else {
            holder.statusBadge.text = "Pending"
            holder.statusBadge.setBackgroundResource(R.drawable.pending_background)
            holder.acceptButton.visibility = View.VISIBLE
        }

        holder.acceptButton.setOnClickListener {
            onAccept(appointment)
        }

        holder.viewHistoryButton.setOnClickListener {
            onViewHistory(appointment)
        }
    }

    override fun getItemCount() = appointments.size
}