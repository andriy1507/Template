package com.spaceapps.template.core.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.spaceapps.template.core.local.db.entity.DummyEntity

@Dao
interface DummyDao {
    @Query("SELECT * FROM DummyEntity")
    suspend fun selectAllDummy(): List<DummyEntity>
}
