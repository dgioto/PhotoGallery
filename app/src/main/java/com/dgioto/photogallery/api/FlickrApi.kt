package com.dgioto.photogallery.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FlickrApi {

    //Определение запроса "Получить недавние интересные фотографии"
    @GET("services/rest/?method=flickr.interestingness.getList")
    fun fetchPhotos(): Call<FlickResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>

    //Добавление функции поиска во FlickrApi
    @GET("services/rest/?method=flickr.photos.search")
    fun searchPhotos(@Query("text") query: String): Call<FlickResponse>
}