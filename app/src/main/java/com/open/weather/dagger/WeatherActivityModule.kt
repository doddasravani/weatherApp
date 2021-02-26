package com.open.weather.dagger

import com.open.weather.ui.activity.WeatherDetailActivity
import com.open.weather.ui.activity.WeatherListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherActivityModule {

    @ContributesAndroidInjector()
    abstract fun weatherListActivity(): WeatherListActivity

    @ContributesAndroidInjector()
    abstract fun weatherDetailActivity(): WeatherDetailActivity
}