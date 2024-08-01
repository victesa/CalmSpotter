package com.victorkirui.calmspotter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {

    @Insert
    suspend fun insert(journalEntity: JournalEntity)

    @Update
    suspend fun update(journalEntity: JournalEntity)

    @Delete
    suspend fun delete(journalEntity: JournalEntity)

    @Query("SELECT * FROM journal_table ORDER BY date DESC")
    fun getAllJournal(): Flow<List<JournalEntity>>
}