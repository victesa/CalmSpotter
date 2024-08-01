package com.victorkirui.calmspotter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val fullName: String = ""
)
