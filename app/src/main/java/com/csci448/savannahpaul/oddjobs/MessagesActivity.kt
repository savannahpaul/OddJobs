package com.csci448.savannahpaul.oddjobs

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.support.v4.app.Fragment
import android.util.Log

class MessagesActivity: SingleFragmentActivity() {

    companion object {
        private val LOG_TAG = "448.MessagesAct"

        fun createIntent(context: Context?): Intent {
            val intent = Intent(context, MessagesActivity::class.java)
            return intent
        }
    }

    override fun getLogTag()= LOG_TAG

    override fun createFragment(): Fragment {
        return MessagesFragment.createFragment()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Log.d(LOG_TAG, "onConfigurationChanged() called")
        Log.d(LOG_TAG, "orientation is ${
        when(newConfig?.orientation ?: Configuration.ORIENTATION_UNDEFINED) {
            Configuration.ORIENTATION_LANDSCAPE -> "Landscape"
            Configuration.ORIENTATION_PORTRAIT -> "Portrait"
            else -> "Undefined"
        }}")
    }
}