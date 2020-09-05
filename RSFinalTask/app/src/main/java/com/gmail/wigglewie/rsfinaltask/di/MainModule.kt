package com.gmail.wigglewie.rsfinaltask.di

import com.gmail.wigglewie.rsfinaltask.feature.data.MainRepository
import com.gmail.wigglewie.rsfinaltask.feature.data.database.LocalData
import com.gmail.wigglewie.rsfinaltask.feature.data.network.NetworkData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideRepo(
        localDataSource: LocalData,
        remoteDataSource: NetworkData
    ): MainRepository {
        return MainRepository(localDataSource, remoteDataSource)
    }
}
