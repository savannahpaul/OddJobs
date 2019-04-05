package com.csci448.savannahpaul.oddjobs

object JobLab {
    private val jobs: MutableList<Job> = mutableListOf()

    fun getJobs() = jobs

    fun addJob(job: Job){
        jobs.add(job)
    }
}