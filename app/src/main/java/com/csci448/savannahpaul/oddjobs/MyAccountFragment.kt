package com.csci448.savannahpaul.oddjobs

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.PermissionChecker.checkSelfPermission
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import kotlinx.android.synthetic.main.activity_single_fragment.*
import kotlinx.android.synthetic.main.bio_dialog.*
import kotlinx.android.synthetic.main.user_account_layout.*

class MyAccountFragment : Fragment() {
    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
        private var uploaded = false
        private var bioAdded = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_account_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivity()!!.setTitle("My Account")
        var title = NavBarActivity.user.first_name + "'s Account"
        account_page_title.text = title
        user_email_text_view.text = NavBarActivity.user.email
        user_username_text_view.text = NavBarActivity.user.username
        user_balance_textview.text = "$ " + NavBarActivity.user.balance.toString()
        if(bioAdded){
            bio_text_view.text = NavBarActivity.user.bio
        }
        if(uploaded){
            profile_image_view.setImageURI(NavBarActivity.user.image)
        }
        img_pick_btn.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(context as Context, Manifest.permission.READ_EXTERNAL_STORAGE) ==
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

        val `in` = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        view_switcher.inAnimation = `in`

        val out = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right)
        view_switcher.outAnimation = out

        update_bio_button?.setOnClickListener {
            bioAdded = true
            if(view_switcher.currentView == view.findViewById(R.id.bio_text_view)){
                //change to edit text
                view_switcher?.showNext()
                update_bio_button.text = "+"
            } else {
                NavBarActivity.user.bio = bio_edit_text.text.toString()
                view_switcher?.showPrevious()
                bio_text_view.text = NavBarActivity.user.bio
                update_bio_button.text = "[]"
            }
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            profile_image_view.setImageURI(data?.data)
            NavBarActivity.user.image = data?.data as Uri
            uploaded = true
        }
    }


}