package com.victorkirui.calmspotter.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AnxietyEntity::class, JournalEntity::class, NameEntity::class], version = 2, autoMigrations = [
    AutoMigration(from = 1, to = 2)
], exportSchema = true)
@TypeConverters(Converters::class)
abstract class MyDatabase: RoomDatabase() {
    abstract val anxietyDao: AnxietyDao
    abstract val journalDao: JournalDao
    abstract val nameDao: NameDao


}