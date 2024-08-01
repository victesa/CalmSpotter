package com.victorkirui.calmspotter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.calmspotter.data.JournalDao
import com.victorkirui.calmspotter.data.JournalEntity
import com.victorkirui.calmspotter.domain.JournalListDetails
import com.victorkirui.calmspotter.domain.JournalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
    private val journalDao: JournalDao
): ViewModel() {

    private val _allJournal = MutableStateFlow<List<JournalListDetails>>(emptyList())
    val allJournal = _allJournal.asStateFlow()

    val _journalState = MutableStateFlow(JournalState())
    val journalState = _journalState.asStateFlow()

    val _journalCurrentSelected = MutableStateFlow(JournalListDetails())
    val journalCurrentSelected = _journalCurrentSelected.asStateFlow()


    init {
        viewModelScope.launch {
            fetchJournalList()
        }
    }

    fun fetchJournalList(): Flow<List<JournalListDetails>> {
        val see = journalDao.getAllJournal().map { list->
            list.map {
                val journalDetails = JournalListDetails(
                    id = it.id,
                    title = it.title,
                    body = it.body,
                    date = it.date
                )

                journalDetails
            }
        }



        return see
    }

    fun delete(journalListDetails: JournalListDetails){
        viewModelScope.launch {
            journalDao.delete(JournalEntity(
                id = journalListDetails.id,
                title = journalListDetails.title,
                body = journalListDetails.body,
                date = journalListDetails.date))
        }
    }

    fun update(date: Date){
        viewModelScope.launch{
            journalDao.update(
                JournalEntity(id = journalCurrentSelected.value.id,
                    title = journalCurrentSelected.value.title,
                    body = journalCurrentSelected.value.body,
                    date = date)
            )
        }
    }

    fun insert( date: Date){
        viewModelScope.launch {
            journalDao.insert(
                JournalEntity(
                    title = journalState.value.title,
                    body = journalState.value.body,
                    date = date))
        }
    }

    fun bodyChange(value: String){
        _journalState.update {
            it.copy(
                body = value
            )
        }
    }

    fun titleChange(value: String){
        _journalState.update {
            it.copy(
                title = value
            )
        }
    }

}