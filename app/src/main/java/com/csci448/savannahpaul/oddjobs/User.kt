package com.csci448.savannahpaul.oddjobs

import android.content.Intent
import android.net.Uri

data class User (
    var first_name: String? = "",
    var last_name: String? = "",
    var username: String? = "",
    var password: String? = "",
    var email: String? = "",
    var balance: Double? = 0.00,
    var image: Uri? = Intent().data
)