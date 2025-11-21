package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.DoctorBillingAdapter
import com.dentalclinic.app.utils.DatabaseManager

class DoctorBillingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button
    private lateinit var totalEarnings: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_billing)

        recyclerView = findViewById(R.id.billingRecyclerView)
        backButton = findViewById(R.id.backButton)
        totalEarnings = findViewById(R.id.totalEarnings)

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
        val billings = DatabaseManager.getBillingsByDoctorId(currentUser?.id ?: "")

        val total = billings.filter { it.paymentStatus == "paid" }.sumOf { it.amount }
        totalEarnings.text = "Total Earnings: $${"%.2f".format(total)}"

        val adapter = DoctorBillingAdapter(billings)
        recyclerView.adapter = adapter
    }
}
