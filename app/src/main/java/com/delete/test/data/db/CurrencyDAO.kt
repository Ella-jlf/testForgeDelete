package com.delete.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.delete.test.domain.model.entity.Currency
import com.delete.test.domain.model.entity.CurrencyFavorite
import com.delete.test.domain.model.entity.CurrencyRate
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDAO {

    //Currency
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currencies: List<Currency>)

    @Query("DELETE FROM currency")
    fun delete()

    @Query("SELECT * FROM Currency")
    fun getAll(): List<Currency>

    @Query("SELECT * FROM currency ORDER BY tag ASC")
    fun getAllSortedByTagASC(): Flow<List<Currency>>

    @Query("SELECT * FROM currency ORDER BY tag DESC")
    fun getAllSortedByTagDESC(): Flow<List<Currency>>

    @Query("SELECT * FROM currency ORDER BY description ASC")
    fun getAllSortedByDescriptionASC(): Flow<List<Currency>>

    @Query("SELECT * FROM currency ORDER BY description DESC")
    fun getAllAllSortedByDescriptionDesc(): Flow<List<Currency>>


    //CurrencyRate
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(currency: CurrencyRate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(currencies: List<CurrencyRate>)

    @Query("DELETE FROM currencyrate")
    fun deleteRates()

    @Query("DELETE FROM currencyrate WHERE id=:id")
    fun deleteRate(id: Int)

    @Query("SELECT * FROM currencyrate ORDER BY tag ASC")
    fun getRateAllSortedByTagASC(): Flow<List<CurrencyRate>>

    @Query("SELECT * FROM currencyrate ORDER BY tag DESC")
    fun getRateAllSortedByTagDESC(): Flow<List<CurrencyRate>>

    @Query("SELECT * FROM currencyrate ORDER BY description ASC")
    fun getRateAllSortedByDescriptionASC(): Flow<List<CurrencyRate>>

    @Query("SELECT * FROM currencyrate ORDER BY description DESC")
    fun getRateAllAllSortedByDescriptionDesc(): Flow<List<CurrencyRate>>

    //CurrencyFavorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyFavorite(currency: CurrencyFavorite)

    @Query("DELETE FROM currencyfavorite WHERE currencyFrom=:currencyFrom AND currencyTo=:currencyTo")
    fun deleteFavorite(currencyFrom: String, currencyTo: String)

    @Query("DELETE FROM currencyfavorite WHERE id=:id")
    fun deleteFavorite(id: Int)

    @Query("SELECT * FROM currencyfavorite ORDER BY currencyFrom ASC")
    fun getFavoriteAllSortedByTagASC(): Flow<List<CurrencyFavorite>>

    @Query("SELECT * FROM currencyfavorite ORDER BY currencyFrom DESC")
    fun getFavoriteAllSortedByTagDESC(): Flow<List<CurrencyFavorite>>

    @Query("SELECT * FROM currencyfavorite ORDER BY currencyTo ASC")
    fun getFavoriteAllSortedByDescriptionASC(): Flow<List<CurrencyFavorite>>

    @Query("SELECT * FROM currencyfavorite ORDER BY currencyTo DESC")
    fun getFavoriteAllAllSortedByDescriptionDesc(): Flow<List<CurrencyFavorite>>
}