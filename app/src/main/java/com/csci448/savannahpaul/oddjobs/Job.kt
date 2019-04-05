package com.csci448.savannahpaul.oddjobs

import java.util.*

class Job {
    var id : UUID
        private set
    var title : String = ""
    var date : Date = Date()
    var description : String = ""
    var price: Int = 0
    var distance: Int = 0

    init {
        id = UUID.randomUUID()
    }
}