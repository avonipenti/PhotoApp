package com.kotlintutorial.archanavonipenti.photoapp.api

import com.kotlintutorial.archanavonipenti.photoapp.models.PhotoList
import retrofit2.http.GET
import retrofit2.Call

interface PhotoAPI {
    @GET("?key=10176516-609bceb05bdcab0f475e28742&q=nature&image_type=photo")
    fun getPhotos() : Call<PhotoList>
}