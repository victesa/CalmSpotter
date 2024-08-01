package com.victorkirui.calmspotter.domain

import java.util.Date

data class JournalListDetails(
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val date: Date = Date(),
    val isUpdated: Boolean = false
)
