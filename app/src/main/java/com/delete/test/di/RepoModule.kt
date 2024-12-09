package com.delete.test.di

import com.delete.test.data.repository.CurrencyRepoImpl
import com.delete.test.domain.repository.CurrencyRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun provideCurrencyRepo(currencyRepoImpl: CurrencyRepoImpl): CurrencyRepo
}