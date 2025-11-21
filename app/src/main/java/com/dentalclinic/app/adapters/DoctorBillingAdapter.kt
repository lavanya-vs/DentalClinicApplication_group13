package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Billing

class DoctorBillingAdapter(
    private val billings: List<Billing>
) : RecyclerView.Adapter<DoctorBillingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val patientName: TextView = view.findViewById(R.id.patientName)
        val amount: TextView = view.findViewById(R.id.amount)
        val date: TextView = view.findViewById(R.id.date)
        val description: TextView = view.findViewById(R.id.description)
        val paymentStatus: TextView = view.findViewById(R.id.paymentStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor_billing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val billing = billings[position]

        holder.patientName.text = billing.patientName
        holder.amount.text = "$${"%.2f".format(billing.amount)}"
        holder.date.text = billing.date
        holder.description.text = billing.description

        if (billing.paymentStatus == "paid") {
            holder.paymentStatus.text = "Paid"
            holder.paymentStatus.setBackgroundResource(R.drawable.accepted_background)
        } else {
            holder.paymentStatus.text = "Pending"
            holder.paymentStatus.setBackgroundResource(R.drawable.pending_background)
        }
    }

    override fun getItemCount() = billings.size
}