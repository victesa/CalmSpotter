package com.victorkirui.calmspotter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface AnxietyDao {

    @Query("SELECT AVG(anxietyRate) FROM anxietyentity WHERE date >= :startOfTheWeek")
    fun getAverageAnxietyRate(startOfTheWeek: Date): Flow<Int?>

    @Query("SELECT anxietyRate FROM anxietyentity WHERE date >= :currentDay AND date < :nextDay")
    fun getCurrentDateData(currentDay: Date, nextDay: Date): Flow<Int>

    @Insert
    suspend fun insert(anxietyEntity: AnxietyEntity)

    @Query("SELECT anxietyRate FROM anxietyentity WHERE date >= :currentDate")
    fun isRatedToday(currentDate: Date): Flow<Int?>
}