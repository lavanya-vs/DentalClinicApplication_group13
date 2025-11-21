package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.PatientBillingAdapter
import com.dentalclinic.app.models.Billing
import com.dentalclinic.app.utils.DatabaseManager

class PatientBillingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_billing)

        recyclerView = findViewById(R.id.billingRecyclerView)
        backButton = findViewById(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadBillings()

        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        loadBillings()
    }

    private fun loadBillings() {
        val currentUser = DatabaseManager.getCurrentUser()
        val billings = DatabaseManager.getBillingsByPatientId(currentUser?.id ?: "")

        val adapter = PatientBillingAdapter(billings.toMutableList()) { billing ->
            showPaymentDialog(billing)
        }
        recyclerView.adapter = adapter
    }

    private fun showPaymentDialog(billing: Billing) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_payment, null)

        AlertDialog.Builder(this)
            .setTitle("Payment via QR Code")
            .setView(dialogView)
            .setPositiveButton("Confirm Payment") { _, _ ->
                billing.paymentStatus = "paid"
                DatabaseManager.updateBilling(billing)
                Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show()
                loadBillings()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}