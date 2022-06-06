package com.dgioto.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel: ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private  val mutableSearchTerm = MutableLiveData<String>()

    init {
        //Хранение последнего запроса в PhotoGalleryViewModel
        mutableSearchTerm.value = "planets"

        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) {
            searchTerm -> flickrFetchr.searchPhotos(searchTerm)
        }
    }

    fun fetchPhotos(query: String = "") {
        mutableSearchTerm.value = query
    }
}