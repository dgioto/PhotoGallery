package com.dgioto.photogallery.api

import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    //Определение запроса "ПОлучить недавние интересные фотографии"
    @GET("services/rest/?method=flickr.interestingness.getList" +
            "&api_key=196e83b2dd4a8902c960580c3d20dd9e" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun fetchPhotos(): Call<FlickResponse>
}