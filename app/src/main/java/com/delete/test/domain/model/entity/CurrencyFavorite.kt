package com.delete.test.domain.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyFavorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val currencyFrom: String,
    val currencyTo: String,
    val rate: String
)
