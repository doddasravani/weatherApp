package com.open.weather.dagger

import com.open.weather.repository.Repository
import com.open.weather.repository.WeatherRepositoryImpl
import com.open.weather.repository.remote.Constants
import com.open.weather.repository.remote.RemoteSource
import com.open.weather.repository.remote.WeatherRemoteSourceImpl
import com.open.weather.viewmodel.WeatherViewModelFactory
import dagger.Module
import dagger.Provides
import org.intellij.lang.annotations.PrintFormat
import javax.inject.Singleton

@Module
class WeatherModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteSource =
        WeatherRemoteSourceImpl.create(Constants.BASE_URL)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteSource): Repository =
        WeatherRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: Repository):WeatherViewModelFactory{
        return WeatherViewModelFactory(repository)
    }
}