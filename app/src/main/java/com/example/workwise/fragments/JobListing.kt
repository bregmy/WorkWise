package com.example.workwise.fragments

import org.json.JSONArray

class JobListing (
    val title: String,
    val salary_is_predicted: String,
    val contract_time:String,
    val description: String,
)
{
    companion object{
        fun fromJsonArray(JobJsonArray: JSONArray): List<JobListing> {
            val jobsResults= mutableListOf<JobListing>()
            for (i in 0 until JobJsonArray.length()){
                val jobJson= JobJsonArray.getJSONObject(i)
                val locationJson = jobJson.getJSONObject("location")
                val categoryJson = jobJson.getJSONObject("category")
                val companyJson = jobJson.getJSONObject("company")
                jobsResults.add(
                    JobListing(
                        jobJson.getString("title"),
                       jobJson.getString("salary_is_predicted"),
                        locationJson.getString("display_name"),
                        jobJson.getString("description")
                    )
                )
            }
            return jobsResults
        }
    }
}
