package com.yacoding.periodictask

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

class PeriodicTask(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val TAG = PeriodicTask::class.java.simpleName

    override fun doWork(): Result {
        Log.d(TAG, "開始時間： ${Date()}")
        var isSuccess = false
        var random: Int
        var times = 1

        while (!isSuccess && (times <= 3)) {
            Log.d(TAG, "第${times}次嘗試")
            random = (0..5).random()
            Log.d(TAG, "目前數字$random")
            times++
            isSuccess = (random == 2)
        }

        return if (isSuccess) {
            Log.d(TAG, "成功")
            Result.success()
        } else {
            Log.d(TAG, "失敗")
            Result.retry()
        }
    }
}