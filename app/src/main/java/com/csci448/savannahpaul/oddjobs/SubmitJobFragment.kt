package com.csci448.savannahpaul.oddjobs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.submit_job_layout.*

class SubmitJobFragment : Fragment() {
    private lateinit var job: Job

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        job = Job()
        return inflater.inflate(R.layout.submit_job_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()!!.setTitle("Submit A Job")

        create_title.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                job.title = s.toString()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        create_price.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                job.price = s.toString().toInt()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        item_description.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?,
                                           start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?,
                                       start: Int, before: Int, count: Int) {
                job.description = s.toString()
            }
            override fun afterTextChanged(s: Editable) {
            }
        })

        upload_photo_button.setOnClickListener{
            Toast.makeText(context, "You'd now be prompted to upload a photo", Toast.LENGTH_LONG).show()
        }

        add_a_location_button.setOnClickListener{
            Toast.makeText(context, "You'd now be prompted to add a location", Toast.LENGTH_LONG).show()
        }
        submit_button.setOnClickListener{
            JobLab.addJob(job)
        }
    }
}