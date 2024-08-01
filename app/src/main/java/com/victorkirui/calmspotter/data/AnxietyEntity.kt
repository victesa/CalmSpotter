package com.victorkirui.calmspotter.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class AnxietyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val anxietyRate: Int,

    val date: Date?
)
