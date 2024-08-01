package com.victorkirui.calmspotter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import com.victorkirui.calmspotter.data.AnxietyDao
import com.victorkirui.calmspotter.data.JournalDao
import com.victorkirui.calmspotter.data.MyDatabase
import com.victorkirui.calmspotter.data.NameDao
import com.victorkirui.calmspotter.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnxietyModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase{
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "my_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAnxietyDao(db: MyDatabase): AnxietyDao{
        return db.anxietyDao
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideJournalDao(db: MyDatabase): JournalDao{
        return db.journalDao
    }

    @Provides
    @Singleton
    fun provideNameDao(db: MyDatabase): NameDao{
        return  db.nameDao
    }
}