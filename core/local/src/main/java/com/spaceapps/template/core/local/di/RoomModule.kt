package com.spaceapps.template.core.local.di

import android.content.Context
import androidx.room.Room
import com.spaceapps.template.core.local.db.AppDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = "app_database",
    )
}
