package com.dentalclinic.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.activities.DentalGuideActivity

class DentalGuideAdapter(
    private val guides: List<DentalGuideActivity.GuideItem>
) : RecyclerView.Adapter<DentalGuideAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.guideTitle)
        val content: TextView = view.findViewById(R.id.guideContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dental_guide, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val guide = guides[position]
        holder.title.text = guide.title
        holder.content.text = guide.content
    }

    override fun getItemCount() = guides.size
}