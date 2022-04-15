package com.dgioto.photogallery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dgioto.photogallery.api.FlickResponse
import com.dgioto.photogallery.api.FlickrApi
import com.dgioto.photogallery.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {

    private val flickrApi : FlickrApi

    init {
        //Использование объекта Retrofit для создания экземпляра API
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun fetchPhotos(): LiveData<List<GalleryItem>>{

        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        //Получение объекта Call, выполняющего запрос
        val flickrRequest: Call<FlickResponse> = flickrApi.fetchPhotos()
        //Выполнение асинхронного запроса
        flickrRequest.enqueue(object : Callback<FlickResponse> {

            override fun onFailure(call: Call<FlickResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<FlickResponse>,
                                    response: Response<FlickResponse>) {
                Log.d(TAG, "Response received")
                val flickResponse: FlickResponse? = response.body()
                val photoResponse: PhotoResponse? = flickResponse?.photos
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems?: mutableListOf()
                galleryItems = galleryItems.filterNot{
                    it.url.isBlank()
                }
                responseLiveData.value = galleryItems
            }
        })
        return responseLiveData
    }
}