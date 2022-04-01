package com.dgioto.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickApi {

    @GET("/")
    fun fetchContents(): Call<String>
}