package com.csci448.savannahpaul.oddjobs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.job_view_layout.*

class JobViewFragment: Fragment() {
    interface Callbacks {
        fun onJobUpdated()
    }
    companion object {
        private val LOG_TAG = "448.JobViewFrag"
        private lateinit var job: Job

        fun createFragment(j: Job): Fragment {
            var jobviewFrag = JobViewFragment()
            job = j
            return jobviewFrag
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "onViewCreated() called")

        //Set Job Information in View
        var price = "$" + job.price.toString()
        job_price_text_view.text = price
        job_title_text_view.text = job.title
        job_description_text_view.text = job.description

        //Button click listeners here
        request_button.setOnClickListener{
            Toast.makeText(context, "Request to poster will be sent", Toast.LENGTH_LONG).show()
        }

        message_button.setOnClickListener{
            //messages activity
            val intent = MessagesActivity.createIntent(context)
            startActivity(intent)
        }
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
        return inflater.inflate(R.layout.job_view_layout, container, false)
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