package me.sadmansarar.currencykeyboard.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.sadmansarar.currencykeyboard.providers.AppColorProvider
import me.sadmansarar.currencykeyboard.providers.AppStringProvider
import me.sadmansarar.currencykeyboard.providers.ColorProvider
import me.sadmansarar.currencykeyboard.providers.StringProvider


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun providesColorProvider(appColorProvider: AppColorProvider): ColorProvider = appColorProvider

    @Provides
    fun providesStringProvider(appStringProvider: AppStringProvider): StringProvider =
        appStringProvider

}