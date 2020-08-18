package com.yacoding.periodictask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val workTag = "number2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workReguest = PeriodicWorkRequestBuilder<PeriodicTask>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 30, TimeUnit.SECONDS)
            .addTag(workTag)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(workTag, ExistingPeriodicWorkPolicy.REPLACE, workReguest)

    }
}
