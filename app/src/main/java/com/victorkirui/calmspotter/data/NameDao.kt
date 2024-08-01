package com.victorkirui.calmspotter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NameDao {

    @Insert
    fun insertName(nameEntity: NameEntity)

    @Query("SELECT fullName FROM NameEntity")
    fun getUsersName(): Flow<String>
}