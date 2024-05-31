package com.spaceapps.template.core.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DummyEntity(
    @PrimaryKey val id: Int,
)
