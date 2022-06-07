package com.footzone.footzone.repository.main

import com.footzone.footzone.model.Location
import com.footzone.footzone.networking.service.ApiService
import okhttp3.MultipartBody
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUserData(userId: String) = apiService.getUserData(userId)
    suspend fun getUserPlayHistory(userId: String)=apiService.getUserPlayHistory(userId)
    suspend fun updateUserProfilePhoto(userId: String, file: MultipartBody.Part) = apiService.updateUserProfilePhoto(userId,file)

    suspend fun getNearByStadiums(location: Location) =
        apiService.getNearByStadiums(location)

    suspend fun getFavouriteStadiums(userId: String) = apiService.getFavouriteStadiums(userId)

    suspend fun getHolderStadiums(userId: String) = apiService.getHolderStadiums(userId)
}