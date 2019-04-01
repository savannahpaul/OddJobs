package com.csci448.savannahpaul.oddjobs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.submit_job_layout.*

class SubmitJobFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.submit_job_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()!!.setTitle("Submit A Job")

        upload_photo_button.setOnClickListener{
            Toast.makeText(context, "You'd now be prompted to upload a photo", Toast.LENGTH_LONG).show()
        }

        add_a_location_button.setOnClickListener{
            Toast.makeText(context, "You'd now be prompted to add a location", Toast.LENGTH_LONG).show()
        }
        submit_button.setOnClickListener{
            Toast.makeText(context, "Job will now be uploaded!", Toast.LENGTH_LONG).show()
        }
    }
}