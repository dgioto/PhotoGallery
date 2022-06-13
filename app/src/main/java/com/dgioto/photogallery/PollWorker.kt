package com.dgioto.photogallery

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "PollWorker"

class PollWorker(private val context: Context, workerParams: WorkerParameters)
    : Worker(context,workerParams) {

    override fun doWork(): Result {
        Log.i(TAG, "Work request triggered: ")
        return Result.success()
    }

}