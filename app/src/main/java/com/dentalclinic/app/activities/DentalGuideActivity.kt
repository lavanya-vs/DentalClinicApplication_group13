package com.dentalclinic.app.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dentalclinic.app.R
import com.dentalclinic.app.adapters.DentalGuideAdapter

class DentalGuideActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: Button

    data class GuideItem(val title: String, val content: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dental_guide)

        recyclerView = findViewById(R.id.guideRecyclerView)
        backButton = findViewById(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        loadGuide()

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun loadGuide() {
        val guides = listOf(
            GuideItem(
                "Brush Twice Daily",
                "Brush your teeth at least twice a day for 2 minutes each time. Use a soft-bristled toothbrush and fluoride toothpaste. Make sure to brush all surfaces of your teeth."
            ),
            GuideItem(
                "Floss Daily",
                "Flossing removes plaque and food particles between teeth where your toothbrush can't reach. Make it a daily habit, preferably before bedtime."
            ),
            GuideItem(
                "Regular Dental Checkups",
                "Visit your dentist at least twice a year for professional cleaning and checkup. Early detection of dental problems can save you from complicated treatments."
            ),
            GuideItem(
                "Healthy Diet",
                "Limit sugary foods and drinks. Eat plenty of fruits, vegetables, and calcium-rich foods. Drink plenty of water throughout the day."
            ),
            GuideItem(
                "Avoid Tobacco",
                "Smoking and tobacco use can lead to gum disease, tooth decay, and oral cancer. Quitting tobacco is one of the best things you can do for your oral health."
            ),
            GuideItem(
                "Use Mouthwash",
                "Antimicrobial mouthwash can help reduce bacteria in your mouth and freshen your breath. Use it as directed by your dentist."
            ),
            GuideItem(
                "Replace Your Toothbrush",
                "Change your toothbrush every 3-4 months or sooner if the bristles are frayed. Worn bristles won't clean your teeth effectively."
            ),
            GuideItem(
                "Protect Your Teeth",
                "Wear a mouthguard when playing sports. Avoid using your teeth to open packages or bite hard objects like ice."
            ),
            GuideItem(
                "Watch for Warning Signs",
                "Look out for bleeding gums, persistent bad breath, tooth sensitivity, or loose teeth. Contact your dentist if you notice any of these symptoms."
            ),
            GuideItem(
                "Children's Dental Care",
                "Start cleaning your baby's gums even before teeth appear. Limit sugary drinks and snacks. Make the first dental visit by age 1 or when the first tooth appears."
            )
        )

        val adapter = DentalGuideAdapter(guides)
        recyclerView.adapter = adapter
    }
}
