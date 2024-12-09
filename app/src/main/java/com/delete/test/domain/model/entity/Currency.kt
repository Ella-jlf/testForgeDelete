package com.delete.test.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tag: String,
    val description: String,
)
