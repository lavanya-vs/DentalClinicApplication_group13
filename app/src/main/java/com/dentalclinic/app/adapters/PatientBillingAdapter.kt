package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.models.Billing

class PatientBillingAdapter(
    private val billings: MutableList<Billing>,
    private val onPay: (Billing) -> Unit
) : RecyclerView.Adapter<PatientBillingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorName: TextView = view.findViewById(R.id.doctorName)
        val amount: TextView = view.findViewById(R.id.amount)
        val date: TextView = view.findViewById(R.id.date)
        val description: TextView = view.findViewById(R.id.description)
        val paymentStatus: TextView = view.findViewById(R.id.paymentStatus)
        val payButton: Button = view.findViewById(R.id.payButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient_billing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val billing = billings[position]

        holder.doctorName.text = billing.doctorName
        holder.amount.text = "$${"%.2f".format(billing.amount)}"
        holder.date.text = billing.date
        holder.description.text = billing.description

        if (billing.paymentStatus == "paid") {
            holder.paymentStatus.text = "Paid"
            holder.paymentStatus.setBackgroundResource(R.drawable.accepted_background)
            holder.payButton.visibility = View.GONE
        } else {
            holder.paymentStatus.text = "Pending"
            holder.paymentStatus.setBackgroundResource(R.drawable.pending_background)
            holder.payButton.visibility = View.VISIBLE
        }

        holder.payButton.setOnClickListener {
            onPay(billing)
        }
    }

    override fun getItemCount() = billings.size
}