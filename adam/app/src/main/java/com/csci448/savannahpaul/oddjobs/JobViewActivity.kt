package com.csci448.savannahpaul.oddjobs

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

class JobViewActivity: SingleFragmentActivity() {

    companion object {
        private val LOG_TAG = "448.JobViewAct"

        fun createIntent(context: Context?): Intent {
            val intent = Intent(context, JobViewActivity::class.java)
            return intent
        }
    }

    override fun getLogTag()= LOG_TAG

    override fun createFragment(): Fragment {
        return JobViewFragment.createFragment()
    }
}