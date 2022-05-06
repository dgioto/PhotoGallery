package com.dgioto.photogallery

import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

private const val  TAG = "ThumbnailDownloader"

class ThumbnailDownloader<in T> : HandlerThread(TAG), LifecycleObserver {

    private var hasQuit = false

    override fun quit(): Boolean {
        hasQuit = true
        return super.quit()
    }

    //Связь ThumbnailDownloader с жизненным циклом
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun setup(){
        Log.i(TAG, "Starting background thread")
        //Запуск потока ThumbnailDownloader
        start()
        looper
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun tearDown(){
        Log.i(TAG, "Destroying background thread")
        //остановка потока ThumbnailDownloader
        quit()
    }

    fun queueThumbnail(target: T, url: String){
        Log.i(TAG, "Got a URL: $url")
    }
}