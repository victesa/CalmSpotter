package com.victorkirui.calmspotter.ui

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.calmspotter.data.AnxietyDao
import com.victorkirui.calmspotter.data.NameDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val anxietyDao: AnxietyDao,
    private val dataStore: DataStore<Preferences>,
    private val nameDao: NameDao
): ViewModel() {

    private val _showAnxietyRate = MutableStateFlow(false)
    val showAnxietyRate = _showAnxietyRate.asStateFlow()


    private val _userHasName = MutableStateFlow(false)
    val userHasName = _userHasName.asStateFlow()

    private val _userName = MutableStateFlow<String?>(null)
    val userName = _userName.asStateFlow()


    init {
        viewModelScope.launch {
            checkIfUserHasAName()


            getAverageAnxietyRate()
        }
    }

    fun isRatedToday(): Flow<Boolean?> {
        var isRated: Boolean? = null

        viewModelScope.launch {
            anxietyDao.isRatedToday(
                Date.from(
                    LocalDate.now()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
                )
            ).collect { anxietyRate ->
                isRated = anxietyRate != null
            }
        }

        return MutableStateFlow(isRated).asStateFlow()
    }


    fun getAverageAnxietyRate(): StateFlow<Int?> {
        val avgAnxietyRateFlow = MutableStateFlow<Int?>(null)

        val firstDayOfTheWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        viewModelScope.launch {
            anxietyDao.getAverageAnxietyRate(Date.from(firstDayOfTheWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .collect { avgAnxietyRateFlow.value = it }
        }

        return avgAnxietyRateFlow.asStateFlow()
    }

    private suspend fun checkIfUserHasAName(){
        var username: String? = null

        nameDao.getUsersName().map {
            username = it
        }.collect()

        _userName.value = username
    }



    fun saveUserName(name: String){
        viewModelScope.launch {
            dataStore.edit {
                it[stringPreferencesKey("Name")] = name
            }
        }
        _userHasName.value = true

        runBlocking {
            checkIfUserHasAName()
        }



    }
}