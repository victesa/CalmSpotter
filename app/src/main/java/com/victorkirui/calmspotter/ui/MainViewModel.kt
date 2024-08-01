package com.victorkirui.calmspotter.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.calmspotter.data.AnxietyDao
import com.victorkirui.calmspotter.data.AnxietyEntity
import com.victorkirui.calmspotter.data.NameDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var anxietyDao: AnxietyDao,
    private var nameDao: NameDao
): ViewModel() {

    var _ratedToday = MutableStateFlow(false)
    var ratedToday = _ratedToday.asStateFlow()

    var _userName: MutableStateFlow<String?> = MutableStateFlow(null)
    var userName = _userName.asStateFlow()

    init {
        viewModelScope.launch {
            checkIfUserHasAName()
        }
    }

    fun saveAnxiety(date: Date,
                    anxietyRate: Int){
        viewModelScope.launch {
            anxietyDao.insert(AnxietyEntity(
                anxietyRate = anxietyRate,
                date = date
            ))
        }
    }

    fun isRatedToday(): Flow<Boolean> {
        return anxietyDao.isRatedToday(
            Date.from(
                LocalDate.now()
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
            )
        ).map { it != null }
    }

    private suspend fun checkIfUserHasAName(){
        var username: String? = null

        nameDao.getUsersName().map {
            username = it
        }.collect()

        _userName.value = username
    }

}