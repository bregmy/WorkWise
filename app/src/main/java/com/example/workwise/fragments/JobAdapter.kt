package com.example.workwise.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workwise.R

class JobAdapter( private val jobsResults: ArrayList<JobListing>): RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val jobTitle = itemView.findViewById<TextView>(R.id.job_title)
        private val companynameTitle = itemView.findViewById<TextView>(R.id.company_name)
        private val jobdescriptionTitle = itemView.findViewById<TextView>(R.id.job_description)
        private val joblocationTitle = itemView.findViewById<TextView>(R.id.job_location)

        fun bind(Job: JobListing) {
            jobTitle.text = Job.title
            companynameTitle.text = Job.contract_time
            jobdescriptionTitle.text = Job.description
            joblocationTitle.text = Job.salary_is_predicted
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=  LayoutInflater.from(parent.context).inflate(R.layout.activity_joblisting, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return jobsResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val job= jobsResults[position]
        holder.bind(job)
    }

}
