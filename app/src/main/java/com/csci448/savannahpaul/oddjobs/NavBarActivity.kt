package com.csci448.savannahpaul.oddjobs

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.nav_bar_layout.*
import kotlinx.android.synthetic.main.nav_header.*
import kotlinx.android.synthetic.main.nav_header.view.*

class NavBarActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private val LOG_TAG = "448.JobSearchAct"
        var user = User()
        var startingPage: Int = 0
        var pageNum: Int = 0

        fun createIntent(context: Context?, u: User, itemId: Int): Intent {
            Log.d(LOG_TAG, "createIntent() called")
            user = u
            startingPage = itemId
            val intent = Intent(context, NavBarActivity::class.java)
            return intent
        }

    }

    fun displaySelectedScreen(itemId: Int) {
        pageNum = itemId
        //creating fragment object
        var fragment: Fragment? = null

        //initializing the fragment object which is selected
        when (itemId) {
            R.id.nav_account -> fragment = MyAccountFragment()
            R.id.nav_job_search -> fragment = JobListFragment()
            R.id.nav_my_jobs -> fragment = JobListFragment() //MyJobsFragment()
            R.id.nav_submit_job -> fragment = SubmitJobFragment()
        }

        //replacing the fragment
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.content_frame, fragment)
            ft.commit()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_bar_layout)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setLogo(R.drawable.thumbnail);
        supportActionBar?.setDisplayUseLogoEnabled(true);

        displaySelectedScreen(startingPage);

        //user_name_text_view.text = user.first_name
        var navView = findViewById<NavigationView>(R.id.nav_view)
        var headerView = navView.getHeaderView(0)
        var nameView = headerView.findViewById<TextView>(R.id.user_name_text_view)
        var balanceView = headerView.findViewById<TextView>(R.id.balance_text_view)
        nameView.text = user.first_name + " " + user.last_name
        balanceView.text = "Balance: $" + user.balance.toString()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.drawer_view, menu)
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displaySelectedScreen(item.getItemId());
        return true
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