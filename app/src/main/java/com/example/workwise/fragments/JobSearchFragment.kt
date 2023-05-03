import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.workwise.R
import com.example.workwise.fragments.JobAdapter
import com.example.workwise.fragments.JobListing
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "JobSearchFragment"
private const val JOB_API_URL = "https://api.adzuna.com/v1/api/jobs/gb/search/1?app_id=5f377078&app_key=66ad6b9bd3ef62fce4171125e22629d6&results_per_page=20&what=javascript%20developer&content-type=application/json"

class JobSearchFragment : Fragment() {

    private val jobListings = ArrayList<JobListing>()
    private lateinit var jobAdapter: JobAdapter
    private lateinit var rvJobs: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobsearch, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvJobs = view.findViewById(R.id.jobs)
        jobAdapter = JobAdapter(jobListings)
        rvJobs.adapter = jobAdapter
        rvJobs.layoutManager = LinearLayoutManager(activity)

        val client = AsyncHttpClient()
        client.get(JOB_API_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, response: JSON?) {
                Log.i(TAG, "onSuccess $response")
                try {
                    val jsonArray = response?.jsonObject?.getJSONArray("results")
                    if (jsonArray != null) {
                        jobListings.addAll(JobListing.fromJsonArray(jsonArray))
                        jobAdapter.notifyDataSetChanged()
                        Log.i(TAG, "Job List $jobListings")
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered Exception: $e")
                }

            }

        })
    }

    private fun parseJobListings(response: JsonHttpResponseHandler.JSON?): List<JobListing> {

        return emptyList()
    }
}
