package com.spaceapps.template.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spaceapps.template.core.local.db.dao.DummyDao
import com.spaceapps.template.core.local.db.entity.DummyEntity

@Database(entities = [DummyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dummyDao(): DummyDao
}
