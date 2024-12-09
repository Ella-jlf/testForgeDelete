package com.delete.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delete.test.domain.model.entity.Currency
import com.delete.test.domain.model.entity.CurrencyFavorite
import com.delete.test.domain.model.entity.CurrencyRate

@Database(
    entities = [Currency::class, CurrencyRate::class, CurrencyFavorite::class],
    version = 5
)
abstract class CurrencyDB : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
}