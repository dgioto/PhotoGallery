package com.dgioto.photogallery

import android.app.Application
import androidx.lifecycle.*

class PhotoGalleryViewModel(private val app: Application): AndroidViewModel(app) {

    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private  val mutableSearchTerm = MutableLiveData<String>()

    init {
        //Хранение последнего запроса в PhotoGalleryViewModel
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)
        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) { searchTerm ->
            //Получение интересных фотографий, когда запрос пустой
            if (searchTerm.isBlank()){
                flickrFetchr.fetchPhotos()
            } else {
                flickrFetchr.searchPhotos(searchTerm)
            }
        }
    }

    fun fetchPhotos(query: String = "") {
        //Сохранение запроса в общих настройках
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
}