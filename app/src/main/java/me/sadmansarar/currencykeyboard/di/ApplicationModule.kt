package me.sadmansarar.currencykeyboard.di

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.sadmansarar.currencykeyboard.App

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun app(@ApplicationContext app: Context) = app as App

    @Provides
    fun providesResources(app: App): Resources = app.resources
}