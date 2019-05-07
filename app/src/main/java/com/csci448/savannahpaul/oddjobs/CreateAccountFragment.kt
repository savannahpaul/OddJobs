package com.csci448.savannahpaul.oddjobs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.create_account.*

class CreateAccountFragment: Fragment() {
    companion object {
        private val LOG_TAG = "448.LoginFrag"
        private lateinit var database: DatabaseReference

        fun createFragment(): Fragment {
            var createaccntFrag = CreateAccountFragment()
            return createaccntFrag
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "onViewCreated() called")

        database = FirebaseDatabase.getInstance().reference
        var pushID: String = database.child("users").push().key as String

        //Button click listeners here
        back_button.setOnClickListener{
            val intent = LoginActivity.createIntent(context)
            startActivity(intent)
        }

        create_account_button.setOnClickListener{
            Log.d(LOG_TAG, "create account button clicked")
            var user = User(first_name_edit_text.text.toString(),
                    last_name_edit_text.text.toString(),
                    username_edit_text.text.toString(),
                    password_edit_text.text.toString(),
                    email_exit_text.text.toString(), 0.00)

            database.child("user").child(pushID).setValue(user)
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            Log.d(LOG_TAG, "Data upload successful")
                        }else{
                            Log.d(LOG_TAG, "Something went wrong :(")
                        }
                    }
            val intent = NavBarActivity.createIntent(context, user, R.id.nav_account)
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
        return inflater.inflate(R.layout.create_account, container, false)
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