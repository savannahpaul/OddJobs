package com.csci448.savannahpaul.oddjobs

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.submit_job_layout.*

class SubmitJobFragment : Fragment() {
    private lateinit var job: Job

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }

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
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (PermissionChecker.checkSelfPermission(context as Context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }

        add_a_location_button.setOnClickListener{
            Toast.makeText(context, "You'd now be prompted to add a location", Toast.LENGTH_LONG).show()
        }
        submit_button.setOnClickListener{
            JobLab.addJob(job)
            val intent = NavBarActivity.createIntent(context, NavBarActivity.user, R.id.nav_job_search)
            startActivity(intent)
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            job.image = data?.data as Uri
            job_image_view.setImageURI(data?.data)
            job_image_view.visibility = VISIBLE
        }
    }

}