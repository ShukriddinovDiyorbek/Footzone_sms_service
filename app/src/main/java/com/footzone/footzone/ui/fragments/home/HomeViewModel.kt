package com.footzone.footzone.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footzone.footzone.model.*
import com.footzone.footzone.model.playhistory.PlayHistoryResponse
import com.footzone.footzone.repository.main.MainRepository
import com.footzone.footzone.utils.UiStateList
import com.footzone.footzone.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _allStadiums =
        MutableStateFlow<UiStateObject<AllStadiumResponse>>(UiStateObject.EMPTY)
    val allStadiums = _allStadiums

    private val _searchedStadiums =
        MutableStateFlow<UiStateObject<StadiumResponse>>(UiStateObject.EMPTY)
    val searchedStadiums = _searchedStadiums

    private val _nearByStadiums =
        MutableStateFlow<UiStateObject<NearStadiumResponse>>(UiStateObject.EMPTY)
    val nearByStadiums = _nearByStadiums

    private val _favouriteStadiums =
        MutableStateFlow<UiStateObject<StadiumResponse>>(UiStateObject.EMPTY)
    val favouriteStadiums = _favouriteStadiums

    private val _previouslyBookedStadiums =
        MutableStateFlow<UiStateObject<StadiumResponse>>(UiStateObject.EMPTY)
    val previouslyBookedStadiums = _previouslyBookedStadiums

    private val _addToFavouriteStadiums =
        MutableStateFlow<UiStateObject<Response>>(UiStateObject.EMPTY)
    val addToFavouriteStadiums = _addToFavouriteStadiums

    private val _addToFavouriteStadiumsDB =
        MutableStateFlow<UiStateObject<Unit>>(UiStateObject.EMPTY)
    val addToFavouriteStadiumsDB = _addToFavouriteStadiumsDB

    private val _getFavouriteStadiumsDB =
        MutableStateFlow<UiStateList<FavouriteStadium>>(UiStateList.EMPTY)
    val getFavouriteStadiumsDB = _getFavouriteStadiumsDB

    fun getNearByStadiums(location: Location) = viewModelScope.launch {
        _nearByStadiums.value = UiStateObject.LOADING

        try {
            val response = mainRepository.getNearByStadiums(location)
            _nearByStadiums.value = UiStateObject.SUCCESS(response)

        } catch (e: Exception) {
            _nearByStadiums.value =
                UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }

    fun getFavouriteStadiums(userId: String) = viewModelScope.launch {
        _favouriteStadiums.value = UiStateObject.LOADING

        try {
            val response = mainRepository.getFavouriteStadiums(userId)
            _favouriteStadiums.value = UiStateObject.SUCCESS(response)

        } catch (e: Exception) {
            _favouriteStadiums.value =
                UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }

    fun getPreviouslyBookedStadiums(userId: String) = viewModelScope.launch {
        _previouslyBookedStadiums.value = UiStateObject.LOADING

        try {
            val response = mainRepository.getUserPlayHistory(userId)
            _previouslyBookedStadiums.value = UiStateObject.SUCCESS(response)

        } catch (e: Exception) {
            _previouslyBookedStadiums.value =
                UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }

    fun addToFavouriteStadiums(favouriteStadiumRequest: FavouriteStadiumRequest) =
        viewModelScope.launch {
            _addToFavouriteStadiums.value = UiStateObject.LOADING

            try {
                val response = mainRepository.addToFavouriteStadiums(favouriteStadiumRequest)
                _addToFavouriteStadiums.value = UiStateObject.SUCCESS(response)

            } catch (e: Exception) {
                _addToFavouriteStadiums.value =
                    UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
            }
        }

    fun getFavouriteStadiumsDB() = viewModelScope.launch {
        _getFavouriteStadiumsDB.value = UiStateList.LOADING

        try {
            val response = mainRepository.getFavouriteStadiumsDB()
            _getFavouriteStadiumsDB.value = UiStateList.SUCCESS(response)

        } catch (e: Exception) {
            _getFavouriteStadiumsDB.value =
                UiStateList.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }

    fun addToFavouriteStadiumsDB(favouriteStadium: FavouriteStadium) = viewModelScope.launch {
        _addToFavouriteStadiumsDB.value = UiStateObject.LOADING

        try {
            val response = mainRepository.addToFavouriteStadiumsDB(favouriteStadium)
            _addToFavouriteStadiumsDB.value = UiStateObject.SUCCESS(response)

        } catch (e: Exception) {
            _addToFavouriteStadiumsDB.value =
                UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }

    fun getAllStadiums() = viewModelScope.launch {
        _allStadiums.value = UiStateObject.LOADING

        try {
            val response = mainRepository.getAllStadiums()
            _allStadiums.value = UiStateObject.SUCCESS(response)

        } catch (e: Exception) {
            _allStadiums.value =
                UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }

    fun getSearchedStadiums(search: String) = viewModelScope.launch {
        _searchedStadiums.value = UiStateObject.LOADING

        try {
            val response = mainRepository.getSearchedStadiums(search)
            _searchedStadiums.value = UiStateObject.SUCCESS(response)

        } catch (e: Exception) {
            _searchedStadiums.value =
                UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
        }
    }
}