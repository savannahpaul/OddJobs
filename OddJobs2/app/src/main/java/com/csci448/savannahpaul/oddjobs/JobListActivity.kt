package com.csci448.savannahpaul.oddjobs

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v4.app.Fragment
import android.util.Log

class JobListActivity: SingleFragmentActivity() {
    companion object {
        private val LOG_TAG = "448.JobListAct"

        fun createIntent(context: Context?): Intent {
            val intent = Intent(context, JobListActivity::class.java)
            return intent
        }
    }

    override fun getLogTag()= LOG_TAG

    override fun createFragment(): Fragment {
        return JobListFragment.createFragment()
    }
}