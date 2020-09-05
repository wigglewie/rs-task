package com.gmail.wigglewie.rsfinaltask.di

import android.app.Application
import com.gmail.wigglewie.rsfinaltask.feature.data.MainRepository
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
        application: Application
    ): MainRepository {
        return MainRepository(application)
    }
}
