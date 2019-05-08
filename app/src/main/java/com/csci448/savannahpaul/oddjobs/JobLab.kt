package com.csci448.savannahpaul.oddjobs

object JobLab {
    private val jobs: MutableList<Job> = mutableListOf()
    private val my_jobs: MutableList<Job> = mutableListOf()
    fun getJobs() = jobs
    fun getMyJobs() = my_jobs
    fun addJob(job: Job){
        jobs.add(job)
    }
    fun addMyJob(job: Job){
        my_jobs.add(job)
    }
}