package com.csci448.savannahpaul.oddjobs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.list_item_job.view.*
import kotlinx.android.synthetic.main.list_item_job.view.*
import kotlinx.android.synthetic.main.activity_login.*

class JobListFragment: Fragment() {
    private	lateinit var adapter: JobListAdapter
    interface Callbacks {
        fun onJobSelected(job: Job, position: Int)
    }
    companion object {
        private val LOG_TAG = "448.JobListFrag"


        fun createFragment(): Fragment {
            var joblistFrag = JobListFragment()
            return joblistFrag
        }
    }

    class JobHolder(val fragment: JobListFragment, val view: View): RecyclerView.ViewHolder(view) {
        fun bind(job: Job, pos: Int) {
            view.list_item_job_title_text_view.text = job.title
            view.list_item_job_description_text_view.text = job.description
            var price = "$" + job.price.toString()
            view.list_item_job_price_text_view.text = price
            view.list_item_job_distance_text_view.text = job.distance.toString()

            view.setOnClickListener {
                //val intent = JobViewActivity.createIntent(fragment.activity, pos)
                //fragment.startActivityForResult(intent, fragment.targetRequestCode)
                //fragment.callbacks?.onCrimeSelected(crime, position)
                val intent = JobViewActivity.createIntent(fragment.activity, job)
                fragment.startActivity(intent)
            }
        }
    }


    private class JobListAdapter(val fragment: JobListFragment, val jobs: List<Job>) : RecyclerView.Adapter<JobHolder>() {

        override fun getItemCount(): Int{
            return jobs.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobHolder {
            val layoutInflater = LayoutInflater.from(fragment.context)
            val view = layoutInflater.inflate(R.layout.list_item_job, parent, false)
            return JobHolder(fragment, view)
        }
        override fun onBindViewHolder(holder: JobHolder, position: Int){
            holder.bind(jobs[position], position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "onViewCreated() called")

        job_list_recycler_view.layoutManager = LinearLayoutManager( activity )
        getActivity()!!.setTitle("Job Search")
        updateUI()

        //Button click listeners here

    }

    private fun updateUI() {
        adapter = JobListAdapter(this, JobLab.getJobs())
        job_list_recycler_view.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "onSaveInstanceState() called")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(LOG_TAG, "onCreateView() called")
        if( savedInstanceState != null ) {
            Log.d(LOG_TAG, "savedInstanceState is not null")

        }
        return inflater.inflate(R.layout.fragment_list, container, false)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate() called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume() called")
    }

    override fun onPause() {
        Log.d(LOG_TAG, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(LOG_TAG, "onStop() called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy() called")
        super.onDestroy()
    }
}